package com.medicalscheduler.infrastructure.web.mapper;

import com.medicalscheduler.domain.entity.User;
import com.medicalscheduler.infrastructure.persistence.entity.UserJpaEntity;
import com.medicalscheduler.infrastructure.web.dto.UserRequest;
import com.medicalscheduler.infrastructure.web.dto.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "jakarta")
public interface UserMapper {

    User toDomain(UserJpaEntity entity);

    UserJpaEntity toJpa(User domain);

    UserResponse toResponse(User domain);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    User toDomain(UserRequest request);
}
