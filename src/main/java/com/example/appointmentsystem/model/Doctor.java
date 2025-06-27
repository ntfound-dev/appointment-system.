package com.example.appointmentsystem.model;

import jakarta.persistence.*;
import java.util.List;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    
    @Column(unique = true)
    private String email;
    
    private String password;
    
    private String specialty;

    // Kriteria Pertanyaan 3: field availableTimes dengan tipe dan anotasi yang sesuai.
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "doctor_available_times", joinColumns = @JoinColumn(name = "doctor_id"))
    @Column(name = "available_time_slot")
    private List<String> availableTimes; // Contoh: ["09:00", "10:00", "11:00"]

    @OneToMany(mappedBy = "doctor")
    @JsonIgnore
    private Set<Appointment> appointments;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getSpecialty() { return specialty; }
    public void setSpecialty(String specialty) { this.specialty = specialty; }
    public List<String> getAvailableTimes() { return availableTimes; }
    public void setAvailableTimes(List<String> availableTimes) { this.availableTimes = availableTimes; }
    public Set<Appointment> getAppointments() { return appointments; }
    public void setAppointments(Set<Appointment> appointments) { this.appointments = appointments; }
}
