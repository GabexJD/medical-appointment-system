package com.medicalscheduler.domain.repository;

import com.medicalscheduler.domain.entity.Doctor;

import java.util.List;
import java.util.Optional;

public interface DoctorRepository {

    Doctor save(Doctor doctor);

    Optional<Doctor> findById(Integer id);

    List<Doctor> findAll();

    void deleteById(Integer id);
}
