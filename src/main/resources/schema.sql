-- Hapus tabel jika sudah ada untuk memastikan awal yang bersih
DROP TABLE IF EXISTS appointment;
DROP TABLE IF EXISTS admin;
DROP TABLE IF EXISTS doctor;
DROP TABLE IF EXISTS patient;


-- Pembuatan tabel-tabel
CREATE TABLE doctor (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    specialty VARCHAR(255)
);

CREATE TABLE patient (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    phone VARCHAR(255),
    password VARCHAR(255) NOT NULL
);

CREATE TABLE admin (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE appointment (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    doctor_id BIGINT NOT NULL,
    patient_id BIGINT NOT NULL,
    appointment_time TIMESTAMP NOT NULL,
    FOREIGN KEY (doctor_id) REFERENCES doctor(id),
    FOREIGN KEY (patient_id) REFERENCES patient(id)
);


-- Data awal dengan hash password yang sudah diperbarui
INSERT INTO doctor (name, email, password, specialty) VALUES
('Dr. Budi Santoso', 'budi.s@example.com', '$2a$10$ZkyFlgHiNSB1S/HQ5pvUAe3bHIyDqIeQKbCTwrnPb7nge8m24kFJK', 'Cardiology'),
('Dr. Citra Lestari', 'citra.l@example.com', '$2a$10$ZkyFlgHiNSB1S/HQ5pvUAe3bHIyDqIeQKbCTwrnPb7nge8m24kFJK', 'Neurology');

INSERT INTO patient (name, email, phone, password) VALUES
('Andi Wijaya', 'andi.w@example.com', '081234567890', '$2a$10$ZkyFlgHiNSB1S/HQ5pvUAe3bHIyDqIeQKbCTwrnPb7nge8m24kFJK'),
('Rina Hartono', 'rina.h@example.com', '081234567891', '$2a$10$ZkyFlgHiNSB1S/HQ5pvUAe3bHIyDqIeQKbCTwrnPb7nge8m24kFJK');

INSERT INTO admin (name, email, password) VALUES
('Super Admin', 'admin@example.com', '$2a$10$ZkyFlgHiNSB1S/HQ5pvUAe3bHIyDqIeQKbCTwrnPb7nge8m24kFJK');
