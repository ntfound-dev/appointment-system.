package com.example.appointmentsystem.dto;

public class DoctorDto {
    private Long id;
    private String name;
    private String specialty;

    public DoctorDto(Long id, String name, String specialty) {
        this.id = id;
        this.name = name;
        this.specialty = specialty;
    }

    // Getters and Setters
    public Long getId() { 
        return id; 
    }

    public void setId(Long id) { 
        this.id = id; 
    }

    public String getName() { 
        return name; 
    }

    public void setName(String name) { 
        this.name = name; 
    }

    public String getSpecialty() { 
        return specialty; 
    }

    public void setSpecialty(String specialty) { 
        this.specialty = specialty; 
    }
}
