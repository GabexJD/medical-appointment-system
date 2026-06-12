package com.medicalscheduler.infrastructure.persistence.repository;

import com.medicalscheduler.domain.entity.Specialty;
import com.medicalscheduler.domain.repository.SpecialtyRepository;
import com.medicalscheduler.infrastructure.persistence.entity.SpecialtyJpaEntity;
import com.medicalscheduler.infrastructure.web.mapper.SpecialtyMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
@Transactional
public class SpecialtyPanacheRepository implements SpecialtyRepository {

    @Inject
    EntityManager entityManager;

    @Inject
    SpecialtyMapper mapper;

    @Override
    public Specialty save(Specialty specialty) {
        SpecialtyJpaEntity entity = mapper.toJpa(specialty);
        if (specialty.getId() == null) {
            entityManager.persist(entity);
        } else {
            entity = entityManager.merge(entity);
        }
        return mapper.toDomain(entity);
    }

    @Override
    public Optional<Specialty> findById(Integer id) {
        SpecialtyJpaEntity entity = entityManager.find(SpecialtyJpaEntity.class, id);
        return Optional.ofNullable(entity).map(mapper::toDomain);
    }

    @Override
    public List<Specialty> findAll() {
        return entityManager.createQuery("SELECT s FROM SpecialtyJpaEntity s", SpecialtyJpaEntity.class)
                .getResultStream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Integer id) {
        entityManager.createQuery("DELETE FROM SpecialtyJpaEntity s WHERE s.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }
}
