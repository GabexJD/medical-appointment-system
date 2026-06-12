package com.medicalscheduler.service;

import com.medicalscheduler.domain.entity.Appointment;
import com.medicalscheduler.domain.entity.AppointmentStatus;
import com.medicalscheduler.domain.entity.Doctor;
import com.medicalscheduler.domain.entity.User;
import com.medicalscheduler.domain.repository.AppointmentRepository;
import com.medicalscheduler.domain.repository.DoctorRepository;
import com.medicalscheduler.domain.repository.UserRepository;
import com.medicalscheduler.infrastructure.web.dto.AppointmentRequest;
import com.medicalscheduler.infrastructure.web.dto.AppointmentResponse;
import com.medicalscheduler.infrastructure.web.mapper.AppointmentMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
@Transactional
public class AppointmentService {

    @Inject
    AppointmentRepository appointmentRepository;

    @Inject
    UserRepository userRepository;

    @Inject
    DoctorRepository doctorRepository;

    @Inject
    AppointmentMapper mapper;

    public List<AppointmentResponse> findByUserId(Integer userId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found with id: " + userId));

        return appointmentRepository.findByUserId(userId).stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    public AppointmentResponse create(AppointmentRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new NotFoundException("User not found with id: " + request.getUserId()));

        Doctor doctor = doctorRepository.findById(request.getDoctorId())
                .orElseThrow(() -> new NotFoundException("Doctor not found with id: " + request.getDoctorId()));

        List<Appointment> userAppointments = appointmentRepository.findByUserIdAndDate(
                user.getId(), request.getAppointmentDate());

        boolean conflictingSpecialty = userAppointments.stream()
                .anyMatch(a -> a.getDoctor().getSpecialty().getId()
                        .equals(doctor.getSpecialty().getId())
                        && a.getStatus() != AppointmentStatus.CANCELLED);

        if (conflictingSpecialty) {
            throw new WebApplicationException(
                    "Patient already has an appointment on this date for the same specialty",
                    Response.Status.CONFLICT);
        }

        boolean slotAlreadyBooked = appointmentRepository
                .findByDoctorAndDateTimeWithLock(doctor.getId(), request.getAppointmentDate(), request.getAppointmentTime())
                .isPresent();

        if (slotAlreadyBooked) {
            throw new WebApplicationException(
                    "This time slot is already booked",
                    Response.Status.CONFLICT);
        }

        Appointment appointment = mapper.toDomain(request);
        appointment.setUser(user);
        appointment.setDoctor(doctor);
        appointment.setStatus(AppointmentStatus.PENDING);
        appointment = appointmentRepository.save(appointment);
        return mapper.toResponse(appointment);
    }
}
