package com.medicalscheduler.infrastructure.persistence.repository;

import com.medicalscheduler.domain.entity.Schedule;
import com.medicalscheduler.domain.repository.ScheduleRepository;
import com.medicalscheduler.infrastructure.persistence.entity.ScheduleJpaEntity;
import com.medicalscheduler.infrastructure.web.mapper.ScheduleMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
@Transactional
public class SchedulePanacheRepository implements ScheduleRepository {

    @Inject
    EntityManager entityManager;

    @Inject
    ScheduleMapper mapper;

    @Override
    public Schedule save(Schedule schedule) {
        ScheduleJpaEntity entity = mapper.toJpa(schedule);
        if (schedule.getId() == null) {
            entityManager.persist(entity);
        } else {
            entity = entityManager.merge(entity);
        }
        return mapper.toDomain(entity);
    }

    @Override
    public List<Schedule> findActiveByDoctorId(Integer doctorId) {
        return entityManager.createQuery(
                "SELECT s FROM ScheduleJpaEntity s WHERE s.doctor.id = :doctorId AND s.isActive = true",
                ScheduleJpaEntity.class)
                .setParameter("doctorId", doctorId)
                .getResultStream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existsActiveByDoctorId(Integer doctorId) {
        Long count = entityManager.createQuery(
                "SELECT COUNT(s) FROM ScheduleJpaEntity s WHERE s.doctor.id = :doctorId AND s.isActive = true",
                Long.class)
                .setParameter("doctorId", doctorId)
                .getSingleResult();
        return count > 0;
    }

    @Override
    public void deleteById(Integer id) {
        entityManager.createQuery("DELETE FROM ScheduleJpaEntity s WHERE s.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }
}
