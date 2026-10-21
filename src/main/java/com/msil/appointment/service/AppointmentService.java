package com.msil.appointment.service;

import com.msil.appointment.dto.request.*;
import com.msil.appointment.dto.response.AppointmentResponse;
import com.msil.appointment.entity.AppointmentStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.UUID;

public interface AppointmentService {
    AppointmentResponse create(CreateAppointmentRequest request);
    AppointmentResponse getById(UUID id);
    Page<AppointmentResponse> getAll(AppointmentStatus status, Pageable pageable);
    AppointmentResponse update(UUID id, UpdateAppointmentRequest request);
    AppointmentResponse assignDriver(UUID id, AssignDriverRequest request);
    void delete(UUID id);
}
