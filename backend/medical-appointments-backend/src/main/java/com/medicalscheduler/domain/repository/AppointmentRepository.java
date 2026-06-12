package com.medicalscheduler.domain.repository;

import com.medicalscheduler.domain.entity.Appointment;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface AppointmentRepository {

    Appointment save(Appointment appointment);

    Optional<Appointment> findById(Integer id);

    List<Appointment> findByUserId(Integer userId);

    List<Appointment> findByDoctorIdAndDate(Integer doctorId, LocalDate date);

    Optional<Appointment> findByDoctorAndDateTimeWithLock(Integer doctorId, LocalDate date, LocalTime time);

    List<Appointment> findByUserIdAndDate(Integer userId, LocalDate date);

    void deleteById(Integer id);
}
