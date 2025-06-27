package com.example.appointmentsystem.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;
    private final UserDetailsServiceImpl userDetailsService;

    public SecurityConfig(JwtAuthFilter jwtAuthFilter, UserDetailsServiceImpl userDetailsService) {
        this.jwtAuthFilter = jwtAuthFilter;
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // 1. Aktifkan CORS menggunakan konfigurasi di bawah
            .cors(withDefaults())
            
            // 2. Nonaktifkan CSRF secara eksplisit untuk API
            .csrf(csrf -> csrf.disable())

            // 3. Atur otorisasi request
            .authorizeHttpRequests(auth -> auth
                // Izinkan semua request ke halaman publik dan file statis
                .requestMatchers("/css/**", "/js/**", "/login.html", "/doctors.html", "/favicon.ico", "/h2-console/**").permitAll()
                // Izinkan semua request ke API login
                .requestMatchers("/api/auth/**").permitAll()
                // Izinkan semua orang melihat daftar dokter
                .requestMatchers("/api/doctors").permitAll()
                // Semua request lain harus diotentikasi
                .anyRequest().authenticated()
            )

            // 4. Atur sesi menjadi STATELESS (tidak ada sesi di server)
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .userDetailsService(userDetailsService)
            
            // 5. Tambahkan filter JWT kita sebelum filter standar
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
            
            // 6. Aturan untuk mengizinkan H2 Console di dalam frame
            .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.sameOrigin()));

        return http.build();
    }

    // Bean untuk konfigurasi CORS
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        // Izinkan SEMUA origin, metode, dan header (umum untuk development)
        configuration.setAllowedOrigins(List.of("*"));
        configuration.setAllowedMethods(List.of("*"));
        configuration.setAllowedHeaders(List.of("*"));
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
