package com.msil.appointment.mapper;

import com.msil.appointment.dto.request.CreateAppointmentRequest;
import com.msil.appointment.dto.response.AppointmentResponse;
import com.msil.appointment.entity.Appointment;

public final class AppointmentMapper {
    private AppointmentMapper() {}

    public static Appointment toEntity(CreateAppointmentRequest r) {
        return Appointment.create(r.customerName(), r.contactNumber(), r.registrationNumber(),
                r.pickupLocation(), r.pickupDate(), r.pickupTime(), r.dropLocation(), r.customerConcerns());
    }

    public static AppointmentResponse toResponse(Appointment a) {
        return new AppointmentResponse(a.getId(), a.getCustomerName(), a.getContactNumber(),
                a.getRegistrationNumber(), a.getPickupLocation(), a.getPickupDate(), a.getPickupTime(),
                a.getDropLocation(), a.getCustomerConcerns(), a.getAssignedDriverId(), a.getStatus(),
                a.getCreatedAt(), a.getUpdatedAt(), a.getVersion());
    }
}
