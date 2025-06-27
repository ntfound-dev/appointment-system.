package com.example.appointmentsystem;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals; // Tambahkan ini

@SpringBootTest
class AppointmentSystemApplicationTests {

    @Test
    void contextLoads() {
        // Tes ini sudah ada, biarkan saja
    }

    // --- TAMBAHKAN TES BARU ANDA DI SINI ---
    @Test
    void addition_isCorrect() {
        // Ini adalah contoh tes sederhana
        assertEquals(4, 2 + 2, "Tes penjumlahan sederhana harus berhasil");
    }

}