package com.medicalscheduler.infrastructure.persistence.repository;

import com.medicalscheduler.domain.entity.Doctor;
import com.medicalscheduler.domain.repository.DoctorRepository;
import com.medicalscheduler.infrastructure.persistence.entity.DoctorJpaEntity;
import com.medicalscheduler.infrastructure.web.mapper.DoctorMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
@Transactional
public class DoctorPanacheRepository implements DoctorRepository {

    @Inject
    EntityManager entityManager;

    @Inject
    DoctorMapper mapper;

    @Override
    public Doctor save(Doctor doctor) {
        DoctorJpaEntity entity = mapper.toJpa(doctor);
        if (doctor.getId() == null) {
            entityManager.persist(entity);
        } else {
            entity = entityManager.merge(entity);
        }
        return mapper.toDomain(entity);
    }

    @Override
    public Optional<Doctor> findById(Integer id) {
        DoctorJpaEntity entity = entityManager.find(DoctorJpaEntity.class, id);
        return Optional.ofNullable(entity).map(mapper::toDomain);
    }

    @Override
    public List<Doctor> findBySpecialtyId(Integer specialtyId) {
        return entityManager.createQuery(
                "SELECT d FROM DoctorJpaEntity d WHERE d.specialty.id = :specialtyId", DoctorJpaEntity.class)
                .setParameter("specialtyId", specialtyId)
                .getResultStream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Doctor> findAll() {
        return entityManager.createQuery("SELECT d FROM DoctorJpaEntity d", DoctorJpaEntity.class)
                .getResultStream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Integer id) {
        entityManager.createQuery("DELETE FROM DoctorJpaEntity d WHERE d.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }
}
