package com.medicalscheduler.infrastructure.web.mapper;

import com.medicalscheduler.domain.entity.Doctor;
import com.medicalscheduler.infrastructure.persistence.entity.DoctorJpaEntity;
import com.medicalscheduler.infrastructure.web.dto.DoctorRequest;
import com.medicalscheduler.infrastructure.web.dto.DoctorResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "jakarta", uses = SpecialtyMapper.class)
public interface DoctorMapper {

    @Mapping(target = "specialty", source = "specialty")
    Doctor toDomain(DoctorJpaEntity entity);

    @Mapping(target = "specialty", source = "specialty")
    DoctorJpaEntity toJpa(Doctor domain);

    @Mapping(target = "specialtyId", source = "specialty.id")
    @Mapping(target = "specialtyName", source = "specialty.name")
    DoctorResponse toResponse(Doctor domain);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "specialty", ignore = true)
    Doctor toDomain(DoctorRequest request);
}
