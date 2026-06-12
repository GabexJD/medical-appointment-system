package com.medicalscheduler.service;

import com.medicalscheduler.domain.entity.Doctor;
import com.medicalscheduler.domain.entity.Specialty;
import com.medicalscheduler.domain.repository.DoctorRepository;
import com.medicalscheduler.domain.repository.ScheduleRepository;
import com.medicalscheduler.domain.repository.SpecialtyRepository;
import com.medicalscheduler.infrastructure.web.dto.SpecialtyRequest;
import com.medicalscheduler.infrastructure.web.dto.SpecialtyResponse;
import com.medicalscheduler.infrastructure.web.mapper.SpecialtyMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
@Transactional
public class SpecialtyService {

    @Inject
    SpecialtyRepository repository;

    @Inject
    DoctorRepository doctorRepository;

    @Inject
    ScheduleRepository scheduleRepository;

    @Inject
    SpecialtyMapper mapper;

    public List<SpecialtyResponse> findAll() {
        List<Specialty> specialties = repository.findAll();
        return specialties.stream()
                .map(specialty -> {
                    SpecialtyResponse response = mapper.toResponse(specialty);
                    response.setHasAvailableSlots(hasAvailableSlots(specialty.getId()));
                    return response;
                })
                .collect(Collectors.toList());
    }

    public SpecialtyResponse findById(Integer id) {
        return repository.findById(id)
                .map(specialty -> {
                    SpecialtyResponse response = mapper.toResponse(specialty);
                    response.setHasAvailableSlots(hasAvailableSlots(specialty.getId()));
                    return response;
                })
                .orElseThrow(() -> new NotFoundException("Specialty not found with id: " + id));
    }

    public SpecialtyResponse create(SpecialtyRequest request) {
        Specialty specialty = mapper.toDomain(request);
        specialty = repository.save(specialty);
        SpecialtyResponse response = mapper.toResponse(specialty);
        response.setHasAvailableSlots(hasAvailableSlots(specialty.getId()));
        return response;
    }

    public SpecialtyResponse update(Integer id, SpecialtyRequest request) {
        Specialty existing = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Specialty not found with id: " + id));
        existing.setName(request.getName());
        existing.setDescription(request.getDescription());
        existing = repository.save(existing);
        SpecialtyResponse response = mapper.toResponse(existing);
        response.setHasAvailableSlots(hasAvailableSlots(existing.getId()));
        return response;
    }

    public void delete(Integer id) {
        if (repository.findById(id).isEmpty()) {
            throw new NotFoundException("Specialty not found with id: " + id);
        }
        repository.deleteById(id);
    }

    private boolean hasAvailableSlots(Integer specialtyId) {
        List<Doctor> doctors = doctorRepository.findBySpecialtyId(specialtyId);
        return doctors.stream().anyMatch(d -> scheduleRepository.existsActiveByDoctorId(d.getId()));
    }
}
