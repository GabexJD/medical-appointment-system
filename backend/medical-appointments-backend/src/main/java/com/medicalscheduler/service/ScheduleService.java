package com.medicalscheduler.service;

import com.medicalscheduler.domain.entity.Appointment;
import com.medicalscheduler.domain.entity.Doctor;
import com.medicalscheduler.domain.entity.Schedule;
import com.medicalscheduler.domain.repository.AppointmentRepository;
import com.medicalscheduler.domain.repository.DoctorRepository;
import com.medicalscheduler.domain.repository.ScheduleRepository;
import com.medicalscheduler.infrastructure.web.dto.AvailabilityResponse;
import com.medicalscheduler.infrastructure.web.dto.TimeSlot;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@ApplicationScoped
public class ScheduleService {

    @Inject
    DoctorRepository doctorRepository;

    @Inject
    ScheduleRepository scheduleRepository;

    @Inject
    AppointmentRepository appointmentRepository;

    public AvailabilityResponse getAvailability(Integer doctorId) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new NotFoundException("Doctor not found with id: " + doctorId));

        List<Schedule> schedules = scheduleRepository.findActiveByDoctorId(doctorId);
        if (schedules.isEmpty()) {
            return new AvailabilityResponse(doctorId, doctor.getFullName(), Collections.emptyList());
        }

        Set<String> activeDays = schedules.stream()
                .map(Schedule::getDayOfWeek)
                .collect(Collectors.toSet());

        LocalDate today = LocalDate.now();
        List<LocalDate> businessDays = new ArrayList<>();
        LocalDate current = today;
        while (businessDays.size() < 6) {
            if (current.getDayOfWeek() != DayOfWeek.SATURDAY
                    && current.getDayOfWeek() != DayOfWeek.SUNDAY) {
                businessDays.add(current);
            }
            current = current.plusDays(1);
        }

        List<TimeSlot> availableSlots = new ArrayList<>();

        for (LocalDate date : businessDays) {
            String dayName = date.getDayOfWeek().name();

            if (!activeDays.contains(dayName)) {
                continue;
            }

            Set<LocalTime> bookedTimes = appointmentRepository.findByDoctorIdAndDate(doctorId, date)
                    .stream()
                    .map(Appointment::getAppointmentTime)
                    .collect(Collectors.toSet());

            List<Schedule> daySchedules = schedules.stream()
                    .filter(s -> s.getDayOfWeek().equals(dayName))
                    .collect(Collectors.toList());

            for (Schedule schedule : daySchedules) {
                LocalTime slotTime = schedule.getStartTime();
                while (slotTime.isBefore(schedule.getEndTime())) {
                    boolean isPast = date.isEqual(today) && !slotTime.isAfter(LocalTime.now());
                    if (!isPast && !bookedTimes.contains(slotTime)) {
                        availableSlots.add(new TimeSlot(date, dayName, slotTime));
                    }
                    slotTime = slotTime.plusMinutes(15);
                }
            }
        }

        availableSlots.sort(Comparator.comparing(TimeSlot::getDate).thenComparing(TimeSlot::getTime));

        return new AvailabilityResponse(doctorId, doctor.getFullName(), availableSlots);
    }
}
