// File: src/main/java/com/example/appointmentsystem/repository/DoctorRepository.java
package com.example.appointmentsystem.repository;

import com.example.appointmentsystem.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Optional<Doctor> findByEmail(String email);
}

