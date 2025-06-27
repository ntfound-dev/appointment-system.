package com.example.appointmentsystem.controller;

import com.example.appointmentsystem.dto.DoctorDto;
import com.example.appointmentsystem.model.Doctor;
import com.example.appointmentsystem.repository.DoctorRepository;
import com.example.appointmentsystem.service.DoctorService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    private final DoctorService doctorService;
    private final DoctorRepository doctorRepository;

    public DoctorController(DoctorService doctorService, DoctorRepository doctorRepository) {
        this.doctorService = doctorService;
        this.doctorRepository = doctorRepository;
    }

    @GetMapping
    public ResponseEntity<List<DoctorDto>> getAllDoctors() {
        List<Doctor> doctors = doctorRepository.findAll();
        List<DoctorDto> doctorDtos = doctors.stream()
                .map(doctor -> new DoctorDto(doctor.getId(), doctor.getName(), doctor.getSpecialty()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(doctorDtos);
    }

    // Kriteria Pertanyaan 5: endpoint GET ketersediaan
    @GetMapping("/{id}/availability")
    @PreAuthorize("isAuthenticated()") // Validasi token dasar
    public ResponseEntity<List<LocalDateTime>> getDoctorAvailability(
            @PathVariable Long id,
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<LocalDateTime> availability = doctorService.getDoctorAvailability(id, date);
        return ResponseEntity.ok(availability); // Respons terstruktur
    }
}
