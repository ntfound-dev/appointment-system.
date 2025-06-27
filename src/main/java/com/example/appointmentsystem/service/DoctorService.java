package com.example.appointmentsystem.service;

import com.example.appointmentsystem.model.Appointment;
import com.example.appointmentsystem.repository.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorService {

    // Menghapus doctorRepository karena tidak digunakan lagi di sini
    private final AppointmentRepository appointmentRepository;

    // Memperbarui constructor
    public DoctorService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    // Kriteria Pertanyaan 10: mengembalikan slot waktu tersedia
    public List<LocalDateTime> getDoctorAvailability(Long doctorId, LocalDate date) {
        
        // Asumsi jam kerja dokter adalah 09:00 - 17:00 dengan slot 1 jam
        List<LocalDateTime> allPossibleSlots = new ArrayList<>();
        LocalDateTime currentSlot = date.atTime(9, 0);
        while(currentSlot.isBefore(date.atTime(17,0))) {
            allPossibleSlots.add(currentSlot);
            currentSlot = currentSlot.plusHours(1);
        }

        // Ambil janji temu yang sudah ada
        List<Appointment> existingAppointments = appointmentRepository.findByDoctorIdAndAppointmentTimeBetween(
                doctorId, date.atStartOfDay(), date.atTime(LocalTime.MAX));

        List<LocalDateTime> bookedSlots = existingAppointments.stream()
                .map(Appointment::getAppointmentTime)
                .collect(Collectors.toList());

        // Hapus slot yang sudah dipesan
        allPossibleSlots.removeAll(bookedSlots);
        return allPossibleSlots;
    }
}
