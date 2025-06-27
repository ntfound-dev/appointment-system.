
// ## File : src/main/java/com/example/appointmentsystem/controller/AppointmentController.java
package com.example.appointmentsystem.controller;

import com.example.appointmentsystem.dto.AppointmentRequestDto;
import com.example.appointmentsystem.dto.AppointmentResponseDto; // Import DTO baru
import com.example.appointmentsystem.model.Appointment;
import com.example.appointmentsystem.model.Patient;
import com.example.appointmentsystem.repository.PatientRepository;
import com.example.appointmentsystem.service.AppointmentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;
    private final PatientRepository patientRepository;

    public AppointmentController(AppointmentService appointmentService, PatientRepository patientRepository) {
        this.appointmentService = appointmentService;
        this.patientRepository = patientRepository;
    }

    @PostMapping("/book")
    @PreAuthorize("hasRole('PATIENT')")
    // Ubah tipe kembalian dari ResponseEntity<Appointment> menjadi ResponseEntity<AppointmentResponseDto>
    public ResponseEntity<AppointmentResponseDto> bookAppointment(
            @Valid @RequestBody AppointmentRequestDto requestDto,
            Principal principal) {
        
        // Dapatkan patient ID dari user yang sedang login
        Patient patient = patientRepository.findByEmail(principal.getName())
                .orElseThrow(() -> new RuntimeException("Patient not found for logged in user"));
        
        // Panggil service untuk membuat janji temu, hasilnya tetap Entitas Appointment
        Appointment newAppointment = appointmentService.book(
                requestDto.getDoctorId(),
                patient.getId(),
                requestDto.getAppointmentTime());

        // **PERBAIKAN UTAMA**: Konversi entitas ke DTO sebelum dikirim sebagai respons
        AppointmentResponseDto responseDto = new AppointmentResponseDto(
                newAppointment.getId(),
                newAppointment.getDoctor().getId(),
                newAppointment.getDoctor().getName(),
                newAppointment.getPatient().getId(),
                newAppointment.getPatient().getName(),
                newAppointment.getAppointmentTime()
        );

        return ResponseEntity.ok(responseDto);
    }
}
