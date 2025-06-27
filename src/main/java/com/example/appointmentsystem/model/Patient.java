// File: src/main/java/com/example/appointmentsystem/model/Patient.java
package com.example.appointmentsystem.model;

import jakarta.persistence.*;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique = true)
    private String email;
    private String phone;
    private String password;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<Appointment> appointments;
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public Set<Appointment> getAppointments() { return appointments; }
    public void setAppointments(Set<Appointment> appointments) { this.appointments = appointments; }
}