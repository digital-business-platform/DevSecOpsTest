package com.msil.appointment.dto.response;

import com.msil.appointment.entity.AppointmentStatus;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public record AppointmentResponse(
        UUID id,
        String customerName,
        String contactNumber,
        String registrationNumber,
        String pickupLocation,
        LocalDate pickupDate,
        LocalTime pickupTime,
        String dropLocation,
        String customerConcerns,
        UUID assignedDriverId,
        AppointmentStatus status,
        Instant createdAt,
        Instant updatedAt,
        Long version
) {}
