package com.example.appointmentsystem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PrescriptionDto {
    @NotNull
    private Long appointmentId;

    @NotBlank
    private String medication;

    @NotBlank
    private String dosage;
    
    private String notes;

    // Getters and Setters
    public Long getAppointmentId() { return appointmentId; }
    public void setAppointmentId(Long appointmentId) { this.appointmentId = appointmentId; }
    public String getMedication() { return medication; }
    public void setMedication(String medication) { this.medication = medication; }
    public String getDosage() { return dosage; }
    public void setDosage(String dosage) { this.dosage = dosage; }
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}
