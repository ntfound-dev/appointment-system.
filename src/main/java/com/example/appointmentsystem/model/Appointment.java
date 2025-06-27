package com.example.appointmentsystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Kriteria Pertanyaan 4: relasi @ManyToOne
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    // Kriteria Pertanyaan 4: field LocalDateTime dengan validasi
    @NotNull
    @Future(message = "Waktu janji temu harus di masa depan")
    @Column(nullable = false)
    private LocalDateTime appointmentTime;

    @OneToOne(mappedBy = "appointment", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Prescription prescription;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Doctor getDoctor() { return doctor; }
    public void setDoctor(Doctor doctor) { this.doctor = doctor; }
    public Patient getPatient() { return patient; }
    public void setPatient(Patient patient) { this.patient = patient; }
    public LocalDateTime getAppointmentTime() { return appointmentTime; }
    public void setAppointmentTime(LocalDateTime appointmentTime) { this.appointmentTime = appointmentTime; }
    public Prescription getPrescription() { return prescription; }
    public void setPrescription(Prescription prescription) { this.prescription = prescription; }
}
