package com.sena.enter.service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.sena.enter.models.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    private Key claveSecreta() {
        String clave = "Enter_Sena_2025_Proyecto_Final_Clave_Secreta_Segura";
        return Keys.hmacShaKeyFor(clave.getBytes(StandardCharsets.UTF_8));
    }

    private final long EXPIRACION = 3600000;

    public String generarToken(User user, String fullName) {
        return Jwts.builder()
                .setSubject(user.getLogin())
                .claim("id", user.getId())
                .claim("nombreCompleto", fullName)
                .claim("rol", user.getAuthorities().stream()
                        .findFirst()
                        .map(auth -> auth.getName())
                        .orElse("USER"))
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRACION))
                .signWith(claveSecreta(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String obtenerUsuario(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(claveSecreta())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean esTokenValido(String token, User user) {
        return obtenerUsuario(token).equals(user.getLogin()) && !estaExpirado(token);
    }

    private boolean estaExpirado(String token) {
        Date expiracion = Jwts.parserBuilder()
                .setSigningKey(claveSecreta())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
        return expiracion.before(new Date());
    }
}