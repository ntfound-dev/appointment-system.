package com.example.appointmentsystem.service;

import com.example.appointmentsystem.model.Appointment;
import com.example.appointmentsystem.model.Doctor;
import com.example.appointmentsystem.model.Patient;
import com.example.appointmentsystem.repository.AppointmentRepository;
import com.example.appointmentsystem.repository.DoctorRepository;
import com.example.appointmentsystem.repository.PatientRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    public AppointmentService(AppointmentRepository appointmentRepository, DoctorRepository doctorRepository, PatientRepository patientRepository) {
        this.appointmentRepository = appointmentRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
    }

    // Kriteria Pertanyaan 6: metode booking
    public Appointment book(Long doctorId, Long patientId, LocalDateTime appointmentTime) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        // Logika validasi bisa ditambahkan di sini (contoh: cek ketersediaan)

        Appointment appointment = new Appointment();
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        appointment.setAppointmentTime(appointmentTime);
        return appointmentRepository.save(appointment);
    }

    // Kriteria Pertanyaan 6: metode mengambil appointment dokter
    public List<Appointment> getAppointmentsByDoctorAndDate(Long doctorId, LocalDate date) {
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(LocalTime.MAX);
        return appointmentRepository.findByDoctorIdAndAppointmentTimeBetween(doctorId, startOfDay, endOfDay);
    }
}
