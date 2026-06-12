package com.medicalscheduler.service;

import com.medicalscheduler.domain.entity.Doctor;
import com.medicalscheduler.domain.entity.Specialty;
import com.medicalscheduler.domain.repository.DoctorRepository;
import com.medicalscheduler.domain.repository.SpecialtyRepository;
import com.medicalscheduler.infrastructure.web.dto.AvailabilityResponse;
import com.medicalscheduler.infrastructure.web.dto.DoctorRequest;
import com.medicalscheduler.infrastructure.web.dto.DoctorResponse;
import com.medicalscheduler.infrastructure.web.mapper.DoctorMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
@Transactional
public class DoctorService {

    @Inject
    DoctorRepository doctorRepository;

    @Inject
    SpecialtyRepository specialtyRepository;

    @Inject
    DoctorMapper mapper;

    @Inject
    ScheduleService scheduleService;

    public List<DoctorResponse> findBySpecialtyId(Integer specialtyId) {
        specialtyRepository.findById(specialtyId)
                .orElseThrow(() -> new NotFoundException("Specialty not found with id: " + specialtyId));
        return doctorRepository.findBySpecialtyId(specialtyId).stream()
                .map(this::toResponseWithAvailability)
                .collect(Collectors.toList());
    }

    public List<DoctorResponse> findAll() {
        return doctorRepository.findAll().stream()
                .map(this::toResponseWithAvailability)
                .collect(Collectors.toList());
    }

    public DoctorResponse findById(Integer id) {
        return doctorRepository.findById(id)
                .map(this::toResponseWithAvailability)
                .orElseThrow(() -> new NotFoundException("Doctor not found with id: " + id));
    }

    public DoctorResponse create(DoctorRequest request) {
        Specialty specialty = specialtyRepository.findById(request.getSpecialtyId())
                .orElseThrow(() -> new NotFoundException("Specialty not found with id: " + request.getSpecialtyId()));

        Doctor doctor = mapper.toDomain(request);
        doctor.setSpecialty(specialty);
        doctor = doctorRepository.save(doctor);
        return toResponseWithAvailability(doctor);
    }

    public DoctorResponse update(Integer id, DoctorRequest request) {
        Doctor existing = doctorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Doctor not found with id: " + id));

        Specialty specialty = specialtyRepository.findById(request.getSpecialtyId())
                .orElseThrow(() -> new NotFoundException("Specialty not found with id: " + request.getSpecialtyId()));

        existing.setFullName(request.getFullName());
        existing.setEmail(request.getEmail());
        existing.setPhone(request.getPhone());
        existing.setSpecialty(specialty);
        existing = doctorRepository.save(existing);
        return toResponseWithAvailability(existing);
    }

    private DoctorResponse toResponseWithAvailability(Doctor doctor) {
        DoctorResponse response = mapper.toResponse(doctor);
        AvailabilityResponse availability = scheduleService.getAvailability(doctor.getId());
        response.setHasAvailableSlots(availability.getAvailableSlots() != null
                && !availability.getAvailableSlots().isEmpty());
        return response;
    }

    public void delete(Integer id) {
        if (doctorRepository.findById(id).isEmpty()) {
            throw new NotFoundException("Doctor not found with id: " + id);
        }
        doctorRepository.deleteById(id);
    }
}
