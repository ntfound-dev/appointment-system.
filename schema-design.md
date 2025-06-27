Desain Skema MySQL:

Tabel: doctors
| Nama Kolom | Tipe Data | Keterangan |
| :--- | :--- | :--- |
| id | BIGINT | Primary Key, Auto Increment |
| name | VARCHAR(255) | Nama lengkap dokter |
| email | VARCHAR(255) | Email unik untuk login |
| password | VARCHAR(255) | Hash password |
| specialty| VARCHAR(255) | Spesialisasi dokter |

Tabel: patients
| Nama Kolom | Tipe Data | Keterangan |
| :--- | :--- | :--- |
| id | BIGINT | Primary Key, Auto Increment |
| name | VARCHAR(255) | Nama lengkap pasien |
| email | VARCHAR(255) | Email unik untuk login |
| phone | VARCHAR(20) | Nomor telepon pasien |
| password | VARCHAR(255) | Hash password |

Tabel: appointments
| Nama Kolom | Tipe Data | Keterangan |
| :--- | :--- | :--- |
| id | BIGINT | Primary Key, Auto Increment |
| doctor_id | BIGINT | Foreign Key ke doctors(id) |
| patient_id| BIGINT | Foreign Key ke patients(id) |
| appointment_time | DATETIME | Waktu janji temu |

Tabel: prescriptions
| Nama Kolom | Tipe Data | Keterangan |
| :--- | :--- | :--- |
| id | BIGINT | Primary Key, Auto Increment |
| appointment_id | BIGINT | Foreign Key ke appointments(id) |
| medication| VARCHAR(255) | Nama obat |
| dosage | VARCHAR(100) | Dosis penggunaan |
| notes | TEXT | Catatan tambahan |