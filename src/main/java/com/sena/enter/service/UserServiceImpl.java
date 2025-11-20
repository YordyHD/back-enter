package com.sena.enter.service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sena.enter.dto.UserDTO;
import com.sena.enter.models.User;
import com.sena.enter.repository.AuthorityRepository;
import com.sena.enter.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthorityRepository authorityRepository;

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

    private String generateTemporaryPassword() {
        return java.util.UUID.randomUUID().toString();
    }
}