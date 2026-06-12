package com.medicalscheduler.infrastructure.web.mapper;

import com.medicalscheduler.domain.entity.Appointment;
import com.medicalscheduler.infrastructure.persistence.entity.AppointmentJpaEntity;
import com.medicalscheduler.infrastructure.web.dto.AppointmentRequest;
import com.medicalscheduler.infrastructure.web.dto.AppointmentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "jakarta", uses = {UserMapper.class, DoctorMapper.class})
public interface AppointmentMapper {

    @Mapping(target = "user", source = "user")
    @Mapping(target = "doctor", source = "doctor")
    Appointment toDomain(AppointmentJpaEntity entity);

    @Mapping(target = "user", source = "user")
    @Mapping(target = "doctor", source = "doctor")
    AppointmentJpaEntity toJpa(Appointment domain);

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "userFullName", source = "user.fullName")
    @Mapping(target = "doctorId", source = "doctor.id")
    @Mapping(target = "doctorFullName", source = "doctor.fullName")
    @Mapping(target = "specialtyId", source = "doctor.specialty.id")
    @Mapping(target = "specialtyName", source = "doctor.specialty.name")
    AppointmentResponse toResponse(Appointment domain);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "doctor", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Appointment toDomain(AppointmentRequest request);
}
