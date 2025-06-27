// File: src/main/java/com/example/appointmentsystem/repository/AppointmentRepository.java
package com.example.appointmentsystem.repository;

import com.example.appointmentsystem.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    // Mencari janji temu untuk seorang dokter dalam rentang waktu tertentu
    List<Appointment> findByDoctorIdAndAppointmentTimeBetween(Long doctorId, LocalDateTime start, LocalDateTime end);
}