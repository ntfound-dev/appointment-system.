package com.example.appointmentsystem.controller;

import com.example.appointmentsystem.dto.PrescriptionDto;
import com.example.appointmentsystem.model.Prescription;
import com.example.appointmentsystem.service.PrescriptionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/prescriptions")
public class PrescriptionController {

    private final PrescriptionService prescriptionService;

    public PrescriptionController(PrescriptionService prescriptionService) {
        this.prescriptionService = prescriptionService;
    }

    // Kriteria Pertanyaan 7: endpoint POST dengan validasi
    @PostMapping
    @PreAuthorize("hasRole('DOCTOR')")
    public ResponseEntity<?> createPrescription(@Valid @RequestBody PrescriptionDto prescriptionDto) {
        try {
            Prescription savedPrescription = prescriptionService.create(prescriptionDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedPrescription);
        } catch (Exception e) {
            // Kriteria Pertanyaan 7: mengembalikan pesan error terstruktur
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
}
