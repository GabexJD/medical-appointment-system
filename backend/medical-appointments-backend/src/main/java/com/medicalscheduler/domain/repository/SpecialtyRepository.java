package com.medicalscheduler.domain.repository;

import com.medicalscheduler.domain.entity.Specialty;

import java.util.List;
import java.util.Optional;

public interface SpecialtyRepository {

    Specialty save(Specialty specialty);

    Optional<Specialty> findById(Integer id);

    List<Specialty> findAll();

    void deleteById(Integer id);
}
