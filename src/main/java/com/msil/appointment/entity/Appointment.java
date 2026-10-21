package com.msil.appointment.entity;

import jakarta.persistence.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Table(name = "appointments", indexes = {
        @Index(name = "idx_appointment_registration", columnList = "registration_number"),
        @Index(name = "idx_appointment_status", columnList = "status"),
        @Index(name = "idx_appointment_pickup_date", columnList = "pickup_date")
})
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "customer_name", nullable = false, length = 255)
    private String customerName;

    @Column(name = "contact_number", nullable = false, length = 20)
    private String contactNumber;

    @Column(name = "registration_number", nullable = false, unique = true, length = 20)
    private String registrationNumber;

    @Column(name = "pickup_location", nullable = false, length = 1000)
    private String pickupLocation;

    @Column(name = "pickup_date", nullable = false)
    private LocalDate pickupDate;

    @Column(name = "pickup_time", nullable = false)
    private LocalTime pickupTime;

    @Column(name = "drop_location", nullable = false, length = 1000)
    private String dropLocation;

    @Column(name = "customer_concerns", length = 2000)
    private String customerConcerns;

    @Column(name = "assigned_driver_id")
    private UUID assignedDriverId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 40)
    private AppointmentStatus status;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @Version
    private Long version;

    protected Appointment() {}

    public static Appointment create(String customerName, String contactNumber, String registrationNumber,
                                     String pickupLocation, LocalDate pickupDate, LocalTime pickupTime,
                                     String dropLocation, String customerConcerns) {
        Appointment a = new Appointment();
        a.customerName = customerName;
        a.contactNumber = contactNumber;
        a.registrationNumber = registrationNumber.toUpperCase();
        a.pickupLocation = pickupLocation;
        a.pickupDate = pickupDate;
        a.pickupTime = pickupTime;
        a.dropLocation = dropLocation;
        a.customerConcerns = customerConcerns;
        a.status = AppointmentStatus.PENDING_ASSIGNMENT;
        a.createdAt = Instant.now();
        a.updatedAt = a.createdAt;
        return a;
    }

    public void update(String customerName, String contactNumber, String pickupLocation,
                       LocalDate pickupDate, LocalTime pickupTime, String dropLocation,
                       String customerConcerns, AppointmentStatus status) {
        this.customerName = customerName;
        this.contactNumber = contactNumber;
        this.pickupLocation = pickupLocation;
        this.pickupDate = pickupDate;
        this.pickupTime = pickupTime;
        this.dropLocation = dropLocation;
        this.customerConcerns = customerConcerns;
        if (status != null) this.status = status;
        this.updatedAt = Instant.now();
    }

    public void assignDriver(UUID driverId) {
        this.assignedDriverId = driverId;
        this.status = AppointmentStatus.DRIVER_ASSIGNED;
        this.updatedAt = Instant.now();
    }

    public UUID getId() { return id; }
    public String getCustomerName() { return customerName; }
    public String getContactNumber() { return contactNumber; }
    public String getRegistrationNumber() { return registrationNumber; }
    public String getPickupLocation() { return pickupLocation; }
    public LocalDate getPickupDate() { return pickupDate; }
    public LocalTime getPickupTime() { return pickupTime; }
    public String getDropLocation() { return dropLocation; }
    public String getCustomerConcerns() { return customerConcerns; }
    public UUID getAssignedDriverId() { return assignedDriverId; }
    public AppointmentStatus getStatus() { return status; }
    public Instant getCreatedAt() { return createdAt; }
    public Instant getUpdatedAt() { return updatedAt; }
    public Long getVersion() { return version; }
}
