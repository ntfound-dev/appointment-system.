**Sistem Janji Temu Dokter**
Repositori ini berisi kode sumber untuk aplikasi web manajemen janji temu medis antara dokter dan pasien. Proyek ini dibangun menggunakan Java dan Spring Boot, dengan frontend sederhana, dan diamankan menggunakan Spring Security & JWT.

---

## Daftar Isi

1. [Tentang Proyek](#tentang-proyek)
2. [Fitur Utama](#fitur-utama)
3. [Teknologi](#teknologi)
4. [Prasyarat](#prasyarat)
5. [Instalasi & Setup](#instalasi--setup)

   * [Kloning Repository](#kloning-repository)
   * [Konfigurasi Database](#konfigurasi-database)
6. [Menjalankan Aplikasi](#menjalankan-aplikasi)

   * [Menggunakan Maven](#menggunakan-maven)
   * [Menggunakan Docker](#menggunakan-docker)
7. [Mengakses Aplikasi](#mengakses-aplikasi)
8. [Pemanfaatan API](#pemanfaatan-api)
9. [Database](#database)
10. [Kontribusi](#kontribusi)
11. [Lisensi](#lisensi)

---

## 1. Tentang Proyek

Aplikasi ini adalah REST API dan aplikasi web sederhana yang memfasilitasi penjadwalan janji temu medis antara dokter dan pasien. Sistem menyediakan antarmuka untuk:

* Pasien memesan janji temu berdasarkan ketersediaan dokter.
* Dokter melihat daftar janji temu dan menambahkan resep setelah konsultasi.
* Admin mengelola data dokter.

## 2. Fitur Utama

* **Peran Pengguna**: Admin, Dokter, dan Pasien dengan hak akses berbeda.
* **Autentikasi & Otorisasi**: Login aman menggunakan JSON Web Token (JWT).
* **Manajemen Janji Temu**: Pasien dapat melihat ketersediaan dokter dan membuat janji.
* **Manajemen Dokter**: Admin dapat menambah, mengubah, dan menghapus data dokter.
* **Manajemen Resep**: Dokter dapat mencatat dan meninjau resep medis.

## 3. Teknologi

* **Backend**: Java 17, Spring Boot 3.2.5, Spring Security, Spring Data JPA (Hibernate)
* **Database**: H2 (In-Memory)
* **Build Tool**: Apache Maven
* **Containerization**: Docker

## 4. Prasyarat

Sebelum memulai, pastikan sudah terinstal:

* Java Development Kit (JDK) versi 17 atau lebih tinggi
* Apache Maven versi 3.8 atau lebih tinggi
* Docker Desktop (opsional, untuk Docker)
* Git

## 5. Instalasi & Setup

### Kloning Repository

```bash
git clone https://github.com/your-username/appointment-system.git
cd appointment-system
```

### Konfigurasi Database

Secara default aplikasi menggunakan H2 in-memory. Jika ingin menggunakan database lain (misal MySQL), sesuaikan properti di `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/nama_db
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

## 6. Menjalankan Aplikasi

### Menggunakan Maven

```bash
mvn spring-boot:run
```

Aplikasi akan berjalan di `http://localhost:8080`.

### Menggunakan Docker

1. **Build image**

   ```bash
   ```

docker build -t appointment-app .

````
2. **Jalankan container**
   ```bash
docker run -d -p 8080:8080 appointment-app
````

## 7. Mengakses Aplikasi

* **Frontend Web**: `http://localhost:8080/login.html`
* **Kredensial Default**:

  * Pasien: `andi.w@example.com` / `password123`
  * Dokter: `budi.s@example.com` / `password123`
  * Admin: `admin@example.com` / `password123`

## 8. Pemanfaatan API

Contoh menggunakan PowerShell:

1. **Login & Dapatkan Token**

   ```powershell
   ```

\$response = Invoke-RestMethod -Uri [http://localhost:8080/api/auth/login](http://localhost:8080/api/auth/login) -Method Post -ContentType "application/json" -Body '{"email":"[andi.w@example.com](mailto:andi.w@example.com)","password":"password123"}'
\$token = \$response.token

````

2. **Buat Header Otorisasi**
   ```powershell
$headers = @{ Authorization = "Bearer $token" }
````

3. **Buat Janji Temu (Pasien)**

   ```powershell
   ```

Invoke-RestMethod -Uri [http://localhost:8080/api/appointments/book](http://localhost:8080/api/appointments/book) -Method Post -ContentType "application/json" -Headers \$headers -Body '{"doctorId":1,"appointmentTime":"2025-07-20T11:00:00"}'

````

4. **Cek Ketersediaan Dokter**
   ```powershell
Invoke-RestMethod -Uri "http://localhost:8080/api/doctors/1/availability?date=2025-07-20" -Method Get -Headers $headers
````

## 9. Database

* Menggunakan H2 in-memory yang di-reset setiap restart aplikasi.
* Skema awal terdefinisi di `src/main/resources/schema.sql`.
* **H2 Console**:

  * URL: `http://localhost:8080/h2-console`
  * JDBC URL: `jdbc:h2:mem:testdb`
  * User: `sa`
  * Password: `password`

## 10. Kontribusi

Kontribusi sangat kami hargai! Silakan:

1. Fork repository
2. Buat branch fitur (`git checkout -b feature/nama-fitur`)
3. Commit perubahan (`git commit -m 'Tambah fitur X'`)
4. Push ke branch Anda (`git push origin feature/nama-fitur`)
5. Buka Pull Request

## 11. Lisensi

Proyek ini dilisensikan di bawah [MIT License](./LICENSE).
