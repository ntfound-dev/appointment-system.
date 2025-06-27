package com.example.appointmentsystem.repository;

import com.example.appointmentsystem.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    // Kriteria Pertanyaan 8: mengambil pasien berdasarkan email
    Optional<Patient> findByEmail(String email);

    // Kriteria Pertanyaan 8: mengambil pasien berdasarkan email atau telepon
    @Query("SELECT p FROM Patient p WHERE p.email = :email OR p.phone = :phone")
    Optional<Patient> findByEmailOrPhone(@Param("email") String email, @Param("phone") String phone);
}
