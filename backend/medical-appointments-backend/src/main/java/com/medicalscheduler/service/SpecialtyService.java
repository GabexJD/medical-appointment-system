package com.medicalscheduler.service;

import com.medicalscheduler.domain.entity.Specialty;
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
    SpecialtyMapper mapper;

    public List<SpecialtyResponse> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    public SpecialtyResponse findById(Integer id) {
        return repository.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new NotFoundException("Specialty not found with id: " + id));
    }

    public SpecialtyResponse create(SpecialtyRequest request) {
        Specialty specialty = mapper.toDomain(request);
        specialty = repository.save(specialty);
        return mapper.toResponse(specialty);
    }

    public SpecialtyResponse update(Integer id, SpecialtyRequest request) {
        Specialty existing = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Specialty not found with id: " + id));
        existing.setName(request.getName());
        existing.setDescription(request.getDescription());
        existing = repository.save(existing);
        return mapper.toResponse(existing);
    }

    public void delete(Integer id) {
        if (repository.findById(id).isEmpty()) {
            throw new NotFoundException("Specialty not found with id: " + id);
        }
        repository.deleteById(id);
    }
}
