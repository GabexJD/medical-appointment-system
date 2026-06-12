package com.medicalscheduler.infrastructure.web.mapper;

import com.medicalscheduler.domain.entity.Schedule;
import com.medicalscheduler.infrastructure.persistence.entity.ScheduleJpaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "jakarta")
public interface ScheduleMapper {

    @Mapping(target = "doctorId", source = "doctor.id")
    Schedule toDomain(ScheduleJpaEntity entity);

    @Mapping(target = "doctor", ignore = true)
    ScheduleJpaEntity toJpa(Schedule domain);
}
