package com.msil.appointment.repository;

import com.msil.appointment.entity.Appointment;
import com.msil.appointment.entity.AppointmentStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface AppointmentRepository extends JpaRepository<Appointment, UUID> {
    boolean existsByRegistrationNumberIgnoreCase(String registrationNumber);
    Page<Appointment> findByStatus(AppointmentStatus status, Pageable pageable);
}
