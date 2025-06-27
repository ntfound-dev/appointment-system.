DELIMITER //

CREATE PROCEDURE GetDailyAppointmentReportByDoctor(IN docId INT, IN rptDate DATE)
BEGIN
  -- Prosedur ini mengambil semua janji temu untuk dokter tertentu
  -- pada tanggal yang spesifik.
  -- Ini menggabungkan tabel Appointment dan Patient untuk mendapatkan nama pasien.
  SELECT
    a.id,
    p.name AS patient_name,
    a.appointment_time
  FROM
    Appointment a
  JOIN
    Patient p ON a.patient_id = p.id
  WHERE
    a.doctor_id = docId AND DATE(a.appointment_time) = rptDate
  ORDER BY
    a.appointment_time;
END //

DELIMITER ;