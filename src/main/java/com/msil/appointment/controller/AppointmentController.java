package com.msil.appointment.controller;


import com.msil.appointment.dto.request.*;
import com.msil.appointment.dto.response.AppointmentResponse;
import com.msil.appointment.entity.AppointmentStatus;
import com.msil.appointment.service.AppointmentService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/appointments")
public class AppointmentController {
    private final AppointmentService service;

    public AppointmentController(AppointmentService service) {
        this.service = service;
    }

    @Operation(summary = "Create a pickup appointment")
    @PostMapping
    public ResponseEntity<AppointmentResponse> create(@Valid @RequestBody CreateAppointmentRequest request) {
        AppointmentResponse response = service.create(request);
        return ResponseEntity.created(URI.create("/api/v1/appointments/" + response.id())).body(response);
    }

    @Operation(summary = "Get an appointment by ID")
    @GetMapping("/{id}")
    public AppointmentResponse getById(@PathVariable UUID id) {
        return service.getById(id);
    }

    @Operation(summary = "List appointments with optional status filter")
    @GetMapping
    public Page<AppointmentResponse> getAll(@RequestParam(required = false) AppointmentStatus status,
            @PageableDefault(size = 20, sort = "createdAt") Pageable pageable) {
        return service.getAll(status, pageable);
    }

    @Operation(summary = "Update an appointment")
    @PutMapping("/{id}")
    public AppointmentResponse update(@PathVariable UUID id,
            @Valid @RequestBody UpdateAppointmentRequest request) {
        return service.update(id, request);
    }

    @Operation(summary = "Assign a driver")
    @PatchMapping("/{id}/driver")
    public AppointmentResponse assignDriver(@PathVariable UUID id,
            @Valid @RequestBody AssignDriverRequest request) {
        return service.assignDriver(id, request);
    }

    @Operation(summary = "Delete an appointment")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}
