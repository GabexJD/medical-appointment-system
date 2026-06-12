package com.medicalscheduler.infrastructure.web.mapper;

import com.medicalscheduler.domain.entity.Specialty;
import com.medicalscheduler.infrastructure.persistence.entity.SpecialtyJpaEntity;
import com.medicalscheduler.infrastructure.web.dto.SpecialtyRequest;
import com.medicalscheduler.infrastructure.web.dto.SpecialtyResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "jakarta")
public interface SpecialtyMapper {

    Specialty toDomain(SpecialtyJpaEntity entity);

    SpecialtyJpaEntity toJpa(Specialty domain);

    SpecialtyResponse toResponse(Specialty domain);

    @Mapping(target = "id", ignore = true)
    Specialty toDomain(SpecialtyRequest request);
}
