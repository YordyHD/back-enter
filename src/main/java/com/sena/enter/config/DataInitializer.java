package com.sena.enter.config;

import java.util.Collections;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.sena.enter.models.Authority;
import com.sena.enter.models.User;
import com.sena.enter.repository.AuthorityRepository;
import com.sena.enter.repository.UserRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository userRepository, AuthorityRepository authorityRepository,
            PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        // 1. Crear el rol ADMIN si no existe
        Authority adminRole = authorityRepository.findById("ADMIN").orElseGet(() -> {
            Authority authority = new Authority();
            authority.setName("ADMIN");
            return authorityRepository.save(authority);
        });

        // 2. Crear el usuario admin si no existe
        if (userRepository.findByLog("admin").isEmpty()) {
            User adminUser = new User();
            adminUser.setLog("admin");
            adminUser.setPassw(passwordEncoder.encode("admin")); // Contraseña: admin
            adminUser.setFName("Admin");
            adminUser.setLName("System");
            adminUser.setEma("admin@localhost");
            adminUser.setActivated(true);
            adminUser.setLangKey("es");
            adminUser.setAuthorities(Collections.singleton(adminRole));

            userRepository.save(adminUser);
            System.out.println("----------------------------------------------------------");
            System.out.println("Usuario ADMIN creado automáticamente:");
            System.out.println("Usuario: admin");
            System.out.println("Contraseña: admin");
            System.out.println("----------------------------------------------------------");
        }
    }
}
