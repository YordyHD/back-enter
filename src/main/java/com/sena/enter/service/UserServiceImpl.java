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

import com.sena.enter.dto.RegisterDTO;
import com.sena.enter.dto.UserDTO;
import com.sena.enter.models.User;
import com.sena.enter.models.Authority;
import com.sena.enter.models.Customer;
import com.sena.enter.repository.AuthorityRepository;
import com.sena.enter.repository.UserRepository;
import com.sena.enter.repository.CustomerRepository;
import com.sena.enter.repository.DocumentTypeRepository;
import com.sena.enter.repository.SexRepository;
import com.sena.enter.repository.CityRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthorityRepository authorityRepository;
    private final CustomerRepository customerRepository;
    private final DocumentTypeRepository documentTypeRepository;
    private final SexRepository sexRepository;
    private final CityRepository cityRepository;

    @Autowired(required = false)
    private JavaMailSender mailSender;
    private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    public UserServiceImpl(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            AuthorityRepository authorityRepository,
            CustomerRepository customerRepository,
            DocumentTypeRepository documentTypeRepository,
            SexRepository sexRepository,
            CityRepository cityRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authorityRepository = authorityRepository;
        this.customerRepository = customerRepository;
        this.documentTypeRepository = documentTypeRepository;
        this.sexRepository = sexRepository;
        this.cityRepository = cityRepository;
    }

    @Override
    public UserDTO register(RegisterDTO registerDTO) {
        if (registerDTO == null) {
            throw new IllegalArgumentException("Register data cannot be null");
        }

        // Validate existence
        if (userRepository.findOneByLogin(registerDTO.getUsername().toLowerCase()).isPresent()) {
            throw new RuntimeException("Username already used");
        }
        if (userRepository.findOneByEmail(registerDTO.getEmail().toLowerCase()).isPresent()) {
            throw new RuntimeException("Email already used");
        }

        // Create User
        User user = new User();
        user.setLogin(registerDTO.getUsername().toLowerCase());
        user.setEmail(registerDTO.getEmail().toLowerCase());
        user.setActivated(true); // Auto-activate for now, or use logic
        user.setLangKey("es"); // Default
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));

        Authority roleUser = authorityRepository.findById("ROLE_USER")
                .orElseGet(() -> {
                    Authority a = new Authority();
                    a.setName("ROLE_USER");
                    return authorityRepository.save(a);
                });
        user.setAuthorities(java.util.Collections.singleton(roleUser));

        User savedUser = userRepository.save(user);

        // Create Customer
        Customer customer = new Customer();
        customer.setUser(savedUser);
        customer.setDocumentNumber(registerDTO.getDocumentNumber());
        customer.setFirstName(registerDTO.getFirstName());
        customer.setSecondName(registerDTO.getSecondName());
        customer.setFirstLastName(registerDTO.getFirstLastName());
        customer.setSecondLastName(registerDTO.getSecondLastName());

        customer.setDocumentType(documentTypeRepository.findById(registerDTO.getDocumentTypeId())
                .orElseThrow(() -> new RuntimeException("Document Type not found")));
        customer.setSex(sexRepository.findById(registerDTO.getSexId())
                .orElseThrow(() -> new RuntimeException("Sex not found")));
        customer.setCity(cityRepository.findById(registerDTO.getCityId())
                .orElseThrow(() -> new RuntimeException("City not found")));

        customerRepository.save(customer);

        return new UserDTO(savedUser);
    }

    @Override
    public UserDTO save(UserDTO userDTO) {
        if (userDTO == null || !isValidUser(userDTO)) {
            return null;
        }

        User user = new User();
        user.setLogin(userDTO.getLogin().toLowerCase());
        // firstName and lastName removed from User
        user.setEmail(userDTO.getEmail().toLowerCase());
        user.setImageUrl(userDTO.getImageUrl());
        user.setActivated(false);
        user.setLangKey(userDTO.getLangKey());

        user.setActivationKey(generateActivationKey());

        String encryptedPassword = passwordEncoder.encode(generateTemporaryPassword());
        user.setPassword(encryptedPassword);

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
                    existingUser.setLogin(userDTO.getLogin().toLowerCase());
                    // firstName and lastName removed from User
                    existingUser.setEmail(userDTO.getEmail().toLowerCase());
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
        return userRepository.findOneByLogin(login)
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
                    user.setPassword(passwordEncoder.encode(newPassword));
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
        return userRepository.findOneByEmail(email.toLowerCase())
                .filter(User::isActivated)
                .map(user -> {
                    String resetKey = generateResetKey();
                    user.setResetKey(resetKey);
                    user.setResetDate(Instant.now());
                    userRepository.save(user);

                    if (mailSender == null) {
                        log.warn(
                                "JavaMailSender bean no configurado. La clave de restablecimiento fue generada pero no enviada por correo: {} -> {}",
                                user.getEmail(), resetKey);
                    } else {
                        try {
                            log.debug("Enviando correo de restablecimiento a {}", user.getEmail());
                            SimpleMailMessage message = new SimpleMailMessage();
                            message.setTo(user.getEmail());
                            message.setSubject("Restablecer contraseña");
                            String resetUrl = "/api/users/reset-password?key=" + resetKey + "&newPassword=...";
                            message.setText("Usa esta clave para restablecer la contraseña: " + resetKey
                                    + "\nEndpoint: " + resetUrl);
                            mailSender.send(message);
                            log.info("Correo de restablecimiento enviado a {}", user.getEmail());
                        } catch (Exception e) {
                            log.error("Error al enviar correo de restablecimiento a {}: {}", user.getEmail(),
                                    e.getMessage(), e);
                        }
                    }

                    return resetKey;
                });
    }

    @Override
    public Optional<String> getActivationKeyByEmail(String email) {
        return userRepository.findOneByEmail(email.toLowerCase())
                .map(user -> user.getActivationKey() != null && !user.getActivationKey().isBlank()
                        ? user.getActivationKey()
                        : null)
                .filter(key -> key != null);
    }

    private String generateTemporaryPassword() {
        return java.util.UUID.randomUUID().toString();
    }
}