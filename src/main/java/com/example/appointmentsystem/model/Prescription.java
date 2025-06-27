package com.example.appointmentsystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Prescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appointment_id", referencedColumnName = "id")
    @JsonIgnore
    private Appointment appointment;

    @NotBlank
    private String medication;

    @NotBlank
    private String dosage;
    
    @Column(columnDefinition = "TEXT")
    private String notes;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Appointment getAppointment() { return appointment; }
    public void setAppointment(Appointment appointment) { this.appointment = appointment; }
    public String getMedication() { return medication; }
    public void setMedication(String medication) { this.medication = medication; }
    public String getDosage() { return dosage; }
    public void setDosage(String dosage) { this.dosage = dosage; }
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}
