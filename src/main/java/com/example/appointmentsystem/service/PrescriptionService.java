package com.example.appointmentsystem.service;

import com.example.appointmentsystem.dto.PrescriptionDto;
import com.example.appointmentsystem.model.Appointment;
import com.example.appointmentsystem.model.Prescription;
import com.example.appointmentsystem.repository.AppointmentRepository;
import com.example.appointmentsystem.repository.PrescriptionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PrescriptionService {

    private final PrescriptionRepository prescriptionRepository;
    private final AppointmentRepository appointmentRepository;

    public PrescriptionService(PrescriptionRepository prescriptionRepository, AppointmentRepository appointmentRepository) {
        this.prescriptionRepository = prescriptionRepository;
        this.appointmentRepository = appointmentRepository;
    }

    @Transactional
    public Prescription create(PrescriptionDto prescriptionDto) {
        Appointment appointment = appointmentRepository.findById(prescriptionDto.getAppointmentId())
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        Prescription prescription = new Prescription();
        prescription.setAppointment(appointment);
        prescription.setMedication(prescriptionDto.getMedication());
        prescription.setDosage(prescriptionDto.getDosage());
        prescription.setNotes(prescriptionDto.getNotes());
        
        return prescriptionRepository.save(prescription);
    }
}
