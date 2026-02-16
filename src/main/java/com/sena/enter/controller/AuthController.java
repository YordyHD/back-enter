package com.sena.enter.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.sena.enter.dto.LoginDto;
import com.sena.enter.dto.RespuestaLoginDto;
import com.sena.enter.models.User;
import com.sena.enter.repository.UserRepository;
import com.sena.enter.repository.CustomerRepository;
import com.sena.enter.service.JwtService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final CustomerRepository customerRepository;

    public AuthController(UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService,
            CustomerRepository customerRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.customerRepository = customerRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto dto) {
        User user = userRepository.findOneByLogin(dto.getUsername())
                .or(() -> userRepository.findOneByEmailIgnoreCase(dto.getUsername()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuario no encontrado"));

        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Contraseña incorrecta");
        }

        String fullName = customerRepository.findByUser(user)
                .map(c -> c.getFirstName() + (c.getFirstLastName() != null ? " " + c.getFirstLastName() : ""))
                .orElse(user.getLogin());

        String token = jwtService.generarToken(user, fullName);

        RespuestaLoginDto respuesta = new RespuestaLoginDto();
        respuesta.setToken(token);
        respuesta.setNombreCompleto(fullName);
        respuesta.setRol(user.getAuthorities().stream()
                .findFirst()
                .map(auth -> auth.getName())
                .orElse("USER"));

        return ResponseEntity.ok(respuesta);
    }
}