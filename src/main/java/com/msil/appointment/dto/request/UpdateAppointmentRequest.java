package com.msil.appointment.dto.request;

import com.msil.appointment.entity.AppointmentStatus;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalTime;

public record UpdateAppointmentRequest(
        @NotBlank @Size(max = 255) String customerName,
        @NotBlank @Pattern(regexp = "^[0-9]{10,15}$", message = "must contain 10 to 15 digits") String contactNumber,
        @NotBlank @Size(max = 1000) String pickupLocation,
        @NotNull @FutureOrPresent LocalDate pickupDate,
        @NotNull LocalTime pickupTime,
        @NotBlank @Size(max = 1000) String dropLocation,
        @Size(max = 2000) String customerConcerns,
        AppointmentStatus status
) {}
