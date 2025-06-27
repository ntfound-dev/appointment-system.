Sistem Janji Temu Dokter
Repositori ini berisi kode sumber untuk aplikasi web manajemen janji temu medis antara dokter dan pasien. Proyek ini dibangun menggunakan Java dan Spring Boot, dengan frontend web sederhana, dan diamankan menggunakan Spring Security & JWT.

Daftar Isi
Tentang Proyek

Fitur Utama

Teknologi

Memulai Proyek

Prasyarat

Instalasi & Setup

Cara Penggunaan

Menjalankan Backend

Menjalankan dengan Docker

Mengakses Aplikasi

Contoh Penggunaan API

Database

1. Tentang Proyek
Proyek ini adalah sebuah REST API dan aplikasi web sederhana untuk memfasilitasi penjadwalan janji temu antara dokter dan pasien.

Fitur Utama
Tiga Peran Pengguna: Admin, Dokter, dan Pasien dengan hak akses berbeda.

Otentikasi & Otorisasi: Sistem login yang aman menggunakan JSON Web Token (JWT).

Manajemen Janji Temu: Pasien dapat mencari dokter, melihat ketersediaan, dan membuat janji temu.

Manajemen Dokter: Admin dapat mengelola data master dokter.

Manajemen Resep: Dokter dapat menambahkan resep medis setelah konsultasi.

Teknologi
Backend: Java 17, Spring Boot 3.2.5, Spring Security

Database: H2 (In-Memory), Spring Data JPA (Hibernate)

Build Tool: Apache Maven

Containerization: Docker

2. Memulai Proyek
Berikut panduan untuk menjalankan proyek ini di mesin lokal Anda.

Prasyarat
Java Development Kit (JDK) versi 17 atau lebih tinggi.

Apache Maven versi 3.8 atau lebih tinggi.

Docker Desktop (opsional, jika ingin menjalankan via Docker).

Git untuk melakukan kloning repositori.

Instalasi & Setup
Clone repositori:

git clone https://github.com/your-username/appointment-system.git
cd appointment-system

Pastikan Konfigurasi Benar:
Buka src/main/resources/application.properties. Pengaturan default sudah menggunakan database H2 in-memory. Jika Anda ingin menggunakan database lain (misal: MySQL), sesuaikan properti spring.datasource.

3. Cara Penggunaan
Menjalankan Backend
Cara termudah untuk menjalankan aplikasi adalah menggunakan Maven wrapper atau Maven yang sudah terinstal.

# Perintah ini akan mengompilasi dan menjalankan aplikasi
mvn spring-boot:run

Setelah beberapa saat, aplikasi akan berjalan di http://localhost:8080.

Menjalankan dengan Docker
Build Image: Perintah ini akan membuat Docker image bernama appointment-app.

docker build -t appointment-app .

Jalankan Kontainer: Perintah ini akan menjalankan aplikasi di dalam kontainer Docker.

docker run -d -p 8080:8080 appointment-app

Mengakses Aplikasi
Frontend Web: Buka browser dan akses http://localhost:8080/login.html.

Kredensial Default:

Pasien: andi.w@example.com / password123

Dokter: budi.s@example.com / password123

Admin: admin@example.com / password123

4. Contoh Penggunaan API
Anda bisa menguji endpoint API menggunakan curl atau alat lain seperti Postman. Berikut contoh menggunakan PowerShell.

1. Login & Dapatkan Token

$response = Invoke-RestMethod -Uri http://localhost:8080/api/auth/login -Method Post -ContentType "application/json" -Body '{"email":"andi.w@example.com", "password":"password123"}'

Token Anda sekarang tersimpan di variabel $response.token.

2. Buat Header Otorisasi

$headers = @{"Authorization" = "Bearer $($response.token)"}

3. Buat Janji Temu (sebagai Pasien)

Invoke-RestMethod -Uri http://localhost:8080/api/appointments/book -Method Post -ContentType "application/json" -Headers $headers -Body '{"doctorId": 1, "appointmentTime": "2025-07-20T11:00:00"}'

4. Lihat Ketersediaan Dokter

Invoke-RestMethod -Uri "http://localhost:8080/api/doctors/1/availability?date=2025-07-20" -Method Get -Headers $headers

5. Database
Aplikasi ini menggunakan H2, sebuah database in-memory yang akan di-reset setiap kali aplikasi di-restart.

Skema Awal: Didefinisikan di src/main/resources/schema.sql.

Akses H2 Console:

Buka browser ke http://localhost:8080/h2-console.

Pastikan pengaturan berikut:

JDBC URL: jdbc:h2:mem:testdb

User Name: sa

Password: password

Klik Connect.
