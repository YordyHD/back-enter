package com.sena.enter.controller;

import com.sena.enter.dto.UserDTO;
import com.sena.enter.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO) {
        try {
            UserDTO created = userService.save(userDTO);
            if (created == null) {
                return ResponseEntity.badRequest().body(Map.of(
                        "mensaje", "Datos de usuario inválidos"
                ));
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                    "mensaje", "Usuario creado exitosamente",
                    "data", created
            ));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(Map.of(
                    "error", "No se pudo crear el usuario",
                    "detalle", e.getMessage()
            ));
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        List<UserDTO> users = userService.findAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        return userService.findOne(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok) 
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("mensaje", "Usuario no encontrado")));
    }

    @GetMapping("/login/{login}")
    public ResponseEntity<?> getUserByLogin(@PathVariable String login) {
        return userService.findByLogin(login)
                .<ResponseEntity<?>>map(ResponseEntity::ok) 
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("mensaje", "Usuario no encontrado")));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        userDTO.setId(id);
        return userService.update(userDTO)
                .<ResponseEntity<?>>map(updated -> ResponseEntity.ok(Map.of(
                        "mensaje", "Usuario actualizado correctamente",
                        "data", updated
                )))
                .orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(Map.of("mensaje", "No se pudo actualizar el usuario")));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        try {
            userService.delete(id);
            return ResponseEntity.ok(Map.of("mensaje", "Usuario eliminado correctamente"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(Map.of(
                    "error", "No se pudo eliminar el usuario",
                    "detalle", e.getMessage()
            ));
        }
    }

    @GetMapping("/activate")
    public ResponseEntity<?> activateUser(@RequestParam String key) {
        return userService.activateRegistration(key)
                .<ResponseEntity<?>>map(user -> ResponseEntity.ok(Map.of(
                        "mensaje", "Usuario activado exitosamente",
                        "data", user
                )))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("mensaje", "Clave de activación inválida o expirada")));
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestParam String key, @RequestParam String newPassword) {
        boolean result = userService.resetPassword(key, newPassword);
        if (result) {
            return ResponseEntity.ok(Map.of("mensaje", "Contraseña restablecida correctamente"));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("mensaje", "Clave de restablecimiento inválida o expirada"));
        }
    }
}