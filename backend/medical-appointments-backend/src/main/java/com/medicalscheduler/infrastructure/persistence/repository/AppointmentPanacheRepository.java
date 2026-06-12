package com.medicalscheduler.infrastructure.persistence.repository;

import com.medicalscheduler.domain.entity.Appointment;
import com.medicalscheduler.domain.repository.AppointmentRepository;
import com.medicalscheduler.infrastructure.persistence.entity.AppointmentJpaEntity;
import com.medicalscheduler.infrastructure.web.mapper.AppointmentMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import jakarta.transaction.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
@Transactional
public class AppointmentPanacheRepository implements AppointmentRepository {

    @Inject
    EntityManager entityManager;

    @Inject
    AppointmentMapper mapper;

    @Override
    public Appointment save(Appointment appointment) {
        AppointmentJpaEntity entity = mapper.toJpa(appointment);
        if (appointment.getId() == null) {
            entityManager.persist(entity);
        } else {
            entity = entityManager.merge(entity);
        }
        return mapper.toDomain(entity);
    }

    @Override
    public Optional<Appointment> findById(Integer id) {
        AppointmentJpaEntity entity = entityManager.find(AppointmentJpaEntity.class, id);
        return Optional.ofNullable(entity).map(mapper::toDomain);
    }

    @Override
    public List<Appointment> findByUserId(Integer userId) {
        return entityManager.createQuery(
                "SELECT a FROM AppointmentJpaEntity a WHERE a.user.id = :userId ORDER BY a.appointmentDate ASC, a.appointmentTime ASC",
                AppointmentJpaEntity.class)
                .setParameter("userId", userId)
                .getResultStream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Appointment> findByDoctorIdAndDate(Integer doctorId, LocalDate date) {
        return entityManager.createQuery(
                "SELECT a FROM AppointmentJpaEntity a WHERE a.doctor.id = :doctorId AND a.appointmentDate = :date",
                AppointmentJpaEntity.class)
                .setParameter("doctorId", doctorId)
                .setParameter("date", date)
                .getResultStream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Appointment> findByDoctorAndDateTimeWithLock(Integer doctorId, LocalDate date, LocalTime time) {
        List<AppointmentJpaEntity> results = entityManager.createQuery(
                "SELECT a FROM AppointmentJpaEntity a WHERE a.doctor.id = :doctorId AND a.appointmentDate = :date AND a.appointmentTime = :time",
                AppointmentJpaEntity.class)
                .setParameter("doctorId", doctorId)
                .setParameter("date", date)
                .setParameter("time", time)
                .setLockMode(LockModeType.PESSIMISTIC_WRITE)
                .getResultList();
        return results.stream().findFirst().map(mapper::toDomain);
    }

    @Override
    public List<Appointment> findByUserIdAndDate(Integer userId, LocalDate date) {
        return entityManager.createQuery(
                "SELECT a FROM AppointmentJpaEntity a WHERE a.user.id = :userId AND a.appointmentDate = :date",
                AppointmentJpaEntity.class)
                .setParameter("userId", userId)
                .setParameter("date", date)
                .getResultStream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Integer id) {
        entityManager.createQuery("DELETE FROM AppointmentJpaEntity a WHERE a.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }
}
