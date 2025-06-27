package com.example.appointmentsystem.controller;

import com.example.appointmentsystem.dto.JwtResponse;
import com.example.appointmentsystem.dto.LoginRequest;
import com.example.appointmentsystem.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public AuthController(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = tokenService.generateToken(authentication);

            return ResponseEntity.ok(new JwtResponse(jwt));
        } catch (BadCredentialsException e) {
            // Jika otentikasi gagal (password/email salah), kembalikan respons error 401
            // dengan pesan JSON yang jelas.
            return ResponseEntity.status(401).body(Map.of("message", "Email atau password salah"));
        }
    }
}
