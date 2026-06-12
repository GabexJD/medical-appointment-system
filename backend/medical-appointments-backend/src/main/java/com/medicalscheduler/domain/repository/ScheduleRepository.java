package com.medicalscheduler.domain.repository;

import com.medicalscheduler.domain.entity.Schedule;

import java.util.List;

public interface ScheduleRepository {

    Schedule save(Schedule schedule);

    List<Schedule> findActiveByDoctorId(Integer doctorId);

    boolean existsActiveByDoctorId(Integer doctorId);

    void deleteById(Integer id);
}
