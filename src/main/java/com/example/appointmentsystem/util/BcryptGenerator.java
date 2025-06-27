package com.example.appointmentsystem.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Ini adalah kelas utilitas SEMENTARA untuk membuat hash password BCrypt.
 * Anda bisa menjalankan file ini secara terpisah untuk mendapatkan hash yang benar.
 */
public class BcryptGenerator {

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "password123";
        String encodedPassword = encoder.encode(rawPassword);

        System.out.println();
        System.out.println("====================================================");
        System.out.println("          BCrypt Password Generator");
        System.out.println("====================================================");
        System.out.println("Raw Password: " + rawPassword);
        System.out.println("Encoded Password: " + encodedPassword);
        System.out.println("====================================================");
        System.out.println("--> Salin nilai 'Encoded Password' di atas dan tempel ke schema.sql");
        System.out.println();
    }
}
