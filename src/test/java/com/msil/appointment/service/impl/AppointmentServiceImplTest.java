package com.msil.appointment.service.impl;

import com.msil.appointment.dto.request.CreateAppointmentRequest;
import com.msil.appointment.entity.Appointment;
import com.msil.appointment.repository.AppointmentRepository;
import org.junit.jupiter.api.*;
import org.mockito.*;
import java.time.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AppointmentServiceImplTest {
    @Mock AppointmentRepository repository;
    @InjectMocks AppointmentServiceImpl service;

    @BeforeEach void setup() { MockitoAnnotations.openMocks(this); }

    @Test void createAppointment() {
        var request = new CreateAppointmentRequest("Neeraj Singh", "9999999999", "UP14AB1234",
                "Noida", LocalDate.now().plusDays(1), LocalTime.of(10, 0), "MSIL Dealer", "Service");
        when(repository.existsByRegistrationNumberIgnoreCase(any())).thenReturn(false);
        when(repository.save(any(Appointment.class))).thenAnswer(i -> i.getArgument(0));
        var response = service.create(request);
        assertEquals("UP14AB1234", response.registrationNumber());
        assertEquals("PENDING_ASSIGNMENT", response.status().name());
    }
}
