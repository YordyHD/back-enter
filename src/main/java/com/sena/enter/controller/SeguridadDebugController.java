package com.sena.enter.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SeguridadDebugController {

    @GetMapping("/api/debug/seguridad")
    public String verificarSeguridad() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null) {
            return " No hay autenticación en el contexto.";
        }

        String usuario = auth.getName();
        String roles = auth.getAuthorities().toString();
        boolean autenticado = auth.isAuthenticated();

        return """
                Usuario autenticado correctamente.
                • Usuario: %s
                • Roles: %s
                • ¿Autenticado?: %s
                """.formatted(usuario, roles, autenticado);
    }
}