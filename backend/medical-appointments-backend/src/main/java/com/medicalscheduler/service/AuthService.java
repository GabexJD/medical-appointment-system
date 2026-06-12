package com.medicalscheduler.service;

import com.medicalscheduler.domain.entity.User;
import com.medicalscheduler.domain.repository.UserRepository;
import com.medicalscheduler.infrastructure.web.dto.LoginRequest;
import com.medicalscheduler.infrastructure.web.dto.LoginResponse;
import com.medicalscheduler.infrastructure.web.dto.UserRequest;
import com.medicalscheduler.infrastructure.web.dto.UserResponse;
import com.medicalscheduler.infrastructure.web.mapper.UserMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
@Transactional
public class AuthService {

    @Inject
    UserRepository userRepository;

    @Inject
    UserMapper mapper;

    public UserResponse register(UserRequest request) {
        User user = mapper.toDomain(request);
        user = userRepository.save(user);
        return mapper.toResponse(user);
    }

    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new WebApplicationException("Invalid credentials", Response.Status.UNAUTHORIZED));

        if (!user.getPassword().equals(request.getPassword())) {
            throw new WebApplicationException("Invalid credentials", Response.Status.UNAUTHORIZED);
        }

        return new LoginResponse(user.getId(), user.getFullName());
    }
}
