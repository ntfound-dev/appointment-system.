
// #####################################################################
// ## File: src/main/java/com/example/appointmentsystem/security/UserDetailsServiceImpl.java
// #####################################################################
package com.example.appointmentsystem.security;

import com.example.appointmentsystem.model.Admin;
import com.example.appointmentsystem.model.Doctor;
import com.example.appointmentsystem.model.Patient;
import com.example.appointmentsystem.repository.AdminRepository;
import com.example.appointmentsystem.repository.DoctorRepository;
import com.example.appointmentsystem.repository.PatientRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final AdminRepository adminRepository;

    public UserDetailsServiceImpl(PatientRepository patientRepository, DoctorRepository doctorRepository, AdminRepository adminRepository) {
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
        this.adminRepository = adminRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Coba cari di repository Admin
        var adminOpt = adminRepository.findByEmail(email);
        if (adminOpt.isPresent()) {
            Admin admin = adminOpt.get();
            List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN"));
            return new User(admin.getEmail(), admin.getPassword(), authorities);
        }

        // Coba cari di repository Doctor
        var doctorOpt = doctorRepository.findByEmail(email);
        if (doctorOpt.isPresent()) {
            Doctor doctor = doctorOpt.get();
            List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_DOCTOR"));
            return new User(doctor.getEmail(), doctor.getPassword(), authorities);
        }

        // Coba cari di repository Patient
        var patientOpt = patientRepository.findByEmail(email);
        if (patientOpt.isPresent()) {
            Patient patient = patientOpt.get();
            List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_PATIENT"));
            return new User(patient.getEmail(), patient.getPassword(), authorities);
        }

        throw new UsernameNotFoundException("User not found with email: " + email);
    }
}
