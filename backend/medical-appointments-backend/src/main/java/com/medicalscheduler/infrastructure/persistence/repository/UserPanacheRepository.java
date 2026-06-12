package com.medicalscheduler.infrastructure.persistence.repository;

import com.medicalscheduler.domain.entity.User;
import com.medicalscheduler.domain.repository.UserRepository;
import com.medicalscheduler.infrastructure.persistence.entity.UserJpaEntity;
import com.medicalscheduler.infrastructure.web.mapper.UserMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
@Transactional
public class UserPanacheRepository implements UserRepository {

    @Inject
    EntityManager entityManager;

    @Inject
    UserMapper mapper;

    @Override
    public User save(User user) {
        UserJpaEntity entity = mapper.toJpa(user);
        if (user.getId() == null) {
            entityManager.persist(entity);
        } else {
            entity = entityManager.merge(entity);
        }
        return mapper.toDomain(entity);
    }

    @Override
    public Optional<User> findById(Integer id) {
        UserJpaEntity entity = entityManager.find(UserJpaEntity.class, id);
        return Optional.ofNullable(entity).map(mapper::toDomain);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        List<UserJpaEntity> results = entityManager.createQuery(
                "SELECT u FROM UserJpaEntity u WHERE u.email = :email", UserJpaEntity.class)
                .setParameter("email", email)
                .getResultList();
        return results.stream().findFirst().map(mapper::toDomain);
    }

    @Override
    public List<User> findAll() {
        return entityManager.createQuery("SELECT u FROM UserJpaEntity u", UserJpaEntity.class)
                .getResultStream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Integer id) {
        entityManager.createQuery("DELETE FROM UserJpaEntity u WHERE u.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }
}
