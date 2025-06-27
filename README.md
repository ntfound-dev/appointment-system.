Proyek Sistem Janji Temu Dokter (Doctor Appointment System)
Repositori ini berisi kode sumber untuk aplikasi web manajemen janji temu antara dokter dan pasien, yang dibangun menggunakan Java dan Spring Boot, serta diamankan dengan JSON Web Tokens (JWT).

1. Ringkasan Aplikasi
Aplikasi ini memfasilitasi proses penjadwalan janji temu medis dengan antarmuka REST API dan frontend web sederhana. Terdapat tiga peran utama:

Admin: Mengelola data master dokter (menambah, mengubah, menghapus) melalui API.

Dokter: Melihat jadwal janji temu yang telah dibuat untuknya.

Pasien: Mencari dokter, melihat ketersediaan, dan membuat janji temu.

Sistem ini menggunakan otentikasi berbasis JWT untuk mengamankan endpoint API, memastikan hanya pengguna yang berwenang yang dapat mengakses data sensitif.

2. Struktur Repositori
Struktur folder proyek telah diperbarui untuk mencakup kelas keamanan dan frontend.

.
├── .github/
│   └── workflows/
│       └── ci.yml
├── docs/
│   ├── screenshots/
│   └── sql/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/appointmentsystem/
│   │   │       ├── controller/  # AuthController, DoctorController, dll.
│   │   │       ├── dto/         # LoginRequest, JwtResponse, dll.
│   │   │       ├── model/       # Entitas JPA
│   │   │       ├── repository/  # Repositori Spring Data JPA
│   │   │       ├── security/    # SecurityConfig, JwtAuthFilter, UserDetailsServiceImpl
│   │   │       └── service/     # AppointmentService, TokenService
│   │   └── resources/
│   │       ├── static/
│   │       │   ├── css/style.css
│   │       │   └── js/script.js
│   │       ├── templates/
│   │       │   ├── login.html
│   │       │   └── doctors.html
│   │       ├── application.properties
│   │       └── schema.sql
│   └── test/
├── Dockerfile
├── pom.xml
└── README.md

Link Cepat ke Bagian Penting:

Konfigurasi Keamanan

Frontend (HTML)

Logika Frontend (JS)

3. Teknologi yang Digunakan
Backend: Java 17, Spring Boot 3.x

Database: H2 (default), JPA/Hibernate

Keamanan: Spring Security, JSON Web Token (JWT)

Build Tool: Apache Maven

Containerization: Docker

CI/CD: GitHub Actions

4. Cara Build dan Menjalankan Aplikasi
Prasyarat
Java JDK 17 atau lebih tinggi.

Apache Maven.

Langkah-langkah
1. Clone Repositori & Build

git clone <URL_REPOSITORY_ANDA>
cd <NAMA_FOLDER_PROYEK>
mvn clean package

2. Jalankan Aplikasi

java -jar target/appointment-system-0.0.1-SNAPSHOT.jar

Aplikasi akan berjalan di http://localhost:8080.

3. Akses Frontend Web
Setelah aplikasi berjalan, Anda bisa mengakses antarmuka web sederhana:

Halaman Login: Buka http://localhost:8080/login.html

Gunakan kredensial dari schema.sql (misal: andi.w@example.com / password123)

Halaman Dokter: Setelah login berhasil, Anda akan diarahkan ke http://localhost:8080/doctors.html.

5. Skrip & Prosedur SQL
Definisi tabel dan data awal ada di src/main/resources/schema.sql. Anda dapat memeriksa isinya melalui H2 Console di http://localhost:8080/h2-console setelah aplikasi berjalan.

6. Contoh Perintah curl untuk Menguji API
Berikut adalah cara berinteraksi dengan API menggunakan curl.

A. Mendapatkan Token Otentikasi
Pertama, lakukan login untuk mendapatkan JWT.

# Kirim request login dengan email dan password
curl -X POST http://localhost:8080/api/auth/login \
-H "Content-Type: application/json" \
-d '{"email":"andi.w@example.com", "password":"password123"}'

Output (Contoh):

{
    "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhbmRpLnc...",
    "type": "Bearer"
}

Simpan nilai token tersebut untuk digunakan pada request berikutnya.

B. Mengakses Endpoint Terproteksi
Gunakan token yang didapat sebagai Bearer token di header Authorization.

# Simpan token ke dalam variabel (bash/zsh)
TOKEN="eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhbmRpLnc..."

# Mendapatkan ketersediaan dokter dengan ID=1 (memerlukan token)
curl -X GET "http://localhost:8080/api/doctors/1/availability?date=2025-06-27" \
-H "Authorization: Bearer $TOKEN"

# Membuat janji temu baru (memerlukan token Pasien)
curl -X POST http://localhost:8080/api/appointments/book \
-H "Content-Type: application/json" \
-H "Authorization: Bearer $TOKEN" \
-d '{"doctorId": 1, "appointmentTime": "2025-06-27T10:00:00"}'

C. Mengakses Endpoint Publik
Endpoint ini tidak memerlukan token otentikasi.

# Mendapatkan daftar semua dokter (diizinkan untuk publik)
curl -X GET http://localhost:8080/api/doctors
