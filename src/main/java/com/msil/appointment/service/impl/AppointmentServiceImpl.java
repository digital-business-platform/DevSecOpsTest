package com.msil.appointment.service.impl;

import com.msil.appointment.dto.request.*;
import com.msil.appointment.dto.response.AppointmentResponse;
import com.msil.appointment.entity.Appointment;
import com.msil.appointment.entity.AppointmentStatus;
import com.msil.appointment.exception.DuplicateRecordException;
import com.msil.appointment.exception.ResourceNotFoundException;
import com.msil.appointment.mapper.AppointmentMapper;
import com.msil.appointment.repository.AppointmentRepository;
import com.msil.appointment.service.AppointmentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;

@Service
@Transactional
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentRepository repository;

    public AppointmentServiceImpl(AppointmentRepository repository) {
        this.repository = repository;
    }

    @Override
    public AppointmentResponse create(CreateAppointmentRequest request) {
        if (repository.existsByRegistrationNumberIgnoreCase(request.registrationNumber())) {
            throw new DuplicateRecordException("Appointment already exists for registration number: " + request.registrationNumber());
        }
        return AppointmentMapper.toResponse(repository.save(AppointmentMapper.toEntity(request)));
    }

    @Override
    @Transactional(readOnly = true)
    public AppointmentResponse getById(UUID id) {
        return AppointmentMapper.toResponse(findEntity(id));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AppointmentResponse> getAll(AppointmentStatus status, Pageable pageable) {
        Page<Appointment> page = status == null ? repository.findAll(pageable) : repository.findByStatus(status, pageable);
        return page.map(AppointmentMapper::toResponse);
    }

    @Override
    public AppointmentResponse update(UUID id, UpdateAppointmentRequest r) {
        Appointment a = findEntity(id);
        a.update(r.customerName(), r.contactNumber(), r.pickupLocation(), r.pickupDate(), r.pickupTime(),
                r.dropLocation(), r.customerConcerns(), r.status());
        return AppointmentMapper.toResponse(repository.save(a));
    }

    @Override
    public AppointmentResponse assignDriver(UUID id, AssignDriverRequest request) {
        Appointment a = findEntity(id);
        a.assignDriver(request.driverId());
        return AppointmentMapper.toResponse(repository.save(a));
    }

    @Override
    public void delete(UUID id) {
        repository.delete(findEntity(id));
    }

    private Appointment findEntity(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found: " + id));
    }
}
