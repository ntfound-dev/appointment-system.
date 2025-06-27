// File: src/main/java/com/example/appointmentsystem/repository/AdminRepository.java
package com.example.appointmentsystem.repository;

import com.example.appointmentsystem.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findByEmail(String email);
}
