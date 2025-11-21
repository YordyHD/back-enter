package com.sena.enter.service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.SimpleMailMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sena.enter.dto.UserDTO;
import com.sena.enter.models.User;
import com.sena.enter.models.Authority;
import com.sena.enter.repository.AuthorityRepository;
import com.sena.enter.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthorityRepository authorityRepository;
    @Autowired(required = false)
    private JavaMailSender mailSender;
    private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    public UserServiceImpl(
        UserRepository userRepository,
        PasswordEncoder passwordEncoder,
        AuthorityRepository authorityRepository
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authorityRepository = authorityRepository;
    }

    @Override
    public UserDTO save(UserDTO userDTO) {
        if (userDTO == null || !isValidUser(userDTO)) {
            return null;
        }

        User user = new User();
        user.setLog(userDTO.getLogin().toLowerCase());
        user.setFName(userDTO.getFirstName());
        user.setLName(userDTO.getLastName());
        user.setEma(userDTO.getEmail().toLowerCase());
        user.setImageUrl(userDTO.getImageUrl());
        user.setActivated(false); 
        user.setLangKey(userDTO.getLangKey());
        
        user.setActivationKey(generateActivationKey());
        
        String encryptedPassword;
        if (userDTO.getPassword() != null && !userDTO.getPassword().isBlank()) {
            encryptedPassword = passwordEncoder.encode(userDTO.getPassword());
        } else {
            encryptedPassword = passwordEncoder.encode(generateTemporaryPassword());
        }
        user.setPassw(encryptedPassword);


        if (userDTO.getAuthorities() != null) {
            user.setAuthorities(userDTO.getAuthorities().stream()
                .map(authorityRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet()));
        }

        user = userRepository.save(user);
        return new UserDTO(user);
    }

    @Override
    public Optional<UserDTO> update(UserDTO userDTO) {
        if (userDTO == null || userDTO.getId() == null || !isValidUser(userDTO)) {
            return Optional.empty();
        }

        return userRepository
            .findById(userDTO.getId())
            .map(existingUser -> {
                existingUser.setLog(userDTO.getLogin().toLowerCase());
                existingUser.setFName(userDTO.getFirstName());
                existingUser.setLName(userDTO.getLastName());
                existingUser.setEma(userDTO.getEmail().toLowerCase());
                existingUser.setImageUrl(userDTO.getImageUrl());
                existingUser.setActivated(userDTO.isActivated());
                existingUser.setLangKey(userDTO.getLangKey());


                if (userDTO.getAuthorities() != null) {
                    existingUser.setAuthorities(userDTO.getAuthorities().stream()
                        .map(authorityRepository::findById)
                        .filter(Optional::isPresent)
                        .map(Optional::get)
                        .collect(Collectors.toSet()));
                }

                return userRepository.save(existingUser);
            })
            .map(UserDTO::new);
    }

    @Override
    public List<UserDTO> findAll() {
        return userRepository.findAll().stream()
            .map(UserDTO::new)
            .collect(Collectors.toList());
    }

    @Override
    public Optional<UserDTO> findOne(Long id) {
        return userRepository.findById(id)
            .map(UserDTO::new);
    }

    @Override
    public Optional<UserDTO> findByLogin(String login) {
        return userRepository.findByLog(login)
            .map(UserDTO::new);
    }

    @Override
    public void delete(Long id) {
        userRepository.findById(id).ifPresent(userRepository::delete);
    }

    @Override
    public boolean addRoleToUser(Long userId, String role) {
        return userRepository.findById(userId).map(user -> {
            Authority authority = authorityRepository.findById(role)
                .orElseGet(() -> {
                    Authority a = new Authority();
                    a.setName(role);
                    return authorityRepository.save(a);
                });
            user.getAuthorities().add(authority);
            userRepository.save(user);
            return true;
        }).orElse(false);
    }

    @Override
    public boolean removeRoleFromUser(Long userId, String role) {
        return userRepository.findById(userId).map(user -> {
            user.getAuthorities().removeIf(a -> a.getName().equals(role));
            userRepository.save(user);
            return true;
        }).orElse(false);
    }

    @Override
    public Optional<UserDTO> activateRegistration(String key) {
        return userRepository.findByActivationKey(key)
            .map(user -> {
                user.setActivated(true);
                user.setActivationKey(null);
                User savedUser = userRepository.save(user);
                return new UserDTO(savedUser);
            });
    }

    @Override
    public boolean resetPassword(String key, String newPassword) {
        return userRepository
            .findByResetKey(key)
            .filter(user -> user.getResetDate().isAfter(Instant.now().minusSeconds(86400)))
            .map(user -> {
                user.setPassw(passwordEncoder.encode(newPassword));
                user.setResetKey(null);
                user.setResetDate(null);
                userRepository.save(user);
                return true;
            })
            .orElse(false);
    }

    private boolean isValidUser(UserDTO userDTO) {
        return userDTO.getLogin() != null &&
               userDTO.getEmail() != null &&
               userDTO.getAuthorities() != null &&
               !userDTO.getAuthorities().isEmpty();
    }

    private String generateActivationKey() {
        return java.util.UUID.randomUUID().toString();
    }

    private String generateResetKey() {
        String uuid = java.util.UUID.randomUUID().toString().replaceAll("-", "");
        String key = uuid.substring(0, Math.min(20, uuid.length()));
        return key.toUpperCase();
    }

    @Override
    public Optional<String> requestPasswordReset(String email) {
        return userRepository.findByEma(email.toLowerCase())
            .filter(User::isActivated)
            .map(user -> {
                String resetKey = generateResetKey();
                user.setResetKey(resetKey);
                user.setResetDate(Instant.now());
                userRepository.save(user);

                if (mailSender == null) {
                    log.warn("JavaMailSender bean no configurado. La clave de restablecimiento fue generada pero no enviada por correo: {} -> {}", user.getEma(), resetKey);
                } else {
                    try {
                        log.debug("Enviando correo de restablecimiento a {}", user.getEma());
                        SimpleMailMessage message = new SimpleMailMessage();
                        message.setTo(user.getEma());
                        message.setSubject("Restablecer contraseña");
                        String resetUrl = "/api/users/reset-password?key=" + resetKey + "&newPassword=...";
                        message.setText("Usa esta clave para restablecer la contraseña: " + resetKey + "\nEndpoint: " + resetUrl);
                        mailSender.send(message);
                        log.info("Correo de restablecimiento enviado a {}", user.getEma());
                    } catch (Exception e) {
                        log.error("Error al enviar correo de restablecimiento a {}: {}", user.getEma(), e.getMessage(), e);
                    }
                }

                return resetKey;
            });
    }

    @Override
    public Optional<String> getActivationKeyByEmail(String email) {
        return userRepository.findByEma(email.toLowerCase())
            .map(user -> user.getActivationKey() != null && !user.getActivationKey().isBlank() ? user.getActivationKey() : null)
            .filter(key -> key != null);
    }

    private String generateTemporaryPassword() {
        return java.util.UUID.randomUUID().toString();
    }
}