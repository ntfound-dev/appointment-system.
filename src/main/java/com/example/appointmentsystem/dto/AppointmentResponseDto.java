// #####################################################################
// ## File BARU: src/main/java/com/example/appointmentsystem/dto/AppointmentResponseDto.java
// ## Buat file baru ini untuk respons API.
// #####################################################################
package com.example.appointmentsystem.dto;

import java.time.LocalDateTime;

public class AppointmentResponseDto {
    private Long id;
    private Long doctorId;
    private String doctorName;
    private Long patientId;
    private String patientName;
    private LocalDateTime appointmentTime;

    // Constructor untuk memudahkan konversi dari Entitas
    public AppointmentResponseDto(Long id, Long doctorId, String doctorName, Long patientId, String patientName, LocalDateTime appointmentTime) {
        this.id = id;
        this.doctorId = doctorId;
        this.doctorName = doctorName;
        this.patientId = patientId;
        this.patientName = patientName;
        this.appointmentTime = appointmentTime;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getDoctorId() { return doctorId; }
    public void setDoctorId(Long doctorId) { this.doctorId = doctorId; }
    public String getDoctorName() { return doctorName; }
    public void setDoctorName(String doctorName) { this.doctorName = doctorName; }
    public Long getPatientId() { return patientId; }
    public void setPatientId(Long patientId) { this.patientId = patientId; }
    public String getPatientName() { return patientName; }
    public void setPatientName(String patientName) { this.patientName = patientName; }
    public LocalDateTime getAppointmentTime() { return appointmentTime; }
    public void setAppointmentTime(LocalDateTime appointmentTime) { this.appointmentTime = appointmentTime; }
}
