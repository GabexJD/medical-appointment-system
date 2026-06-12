package com.medicalscheduler.domain.repository;

import com.medicalscheduler.domain.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    User save(User user);

    Optional<User> findById(Integer id);

    Optional<User> findByEmail(String email);

    List<User> findAll();

    void deleteById(Integer id);
}
