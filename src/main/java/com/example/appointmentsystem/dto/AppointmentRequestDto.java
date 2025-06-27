
// ## File: src/main/java/com/example/appointmentsystem/dto/AppointmentRequestDto.java

package com.example.appointmentsystem.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class AppointmentRequestDto {
    
    @NotNull(message = "Doctor ID tidak boleh kosong")
    private Long doctorId;

    @NotNull(message = "Waktu janji temu tidak boleh kosong")
    private LocalDateTime appointmentTime;

    // Getters and Setters
    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public LocalDateTime getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(LocalDateTime appointmentTime) {
        this.appointmentTime = appointmentTime;
    }
}
