package com.sena.enter.service;

import java.util.List;
import java.util.Optional;

import com.sena.enter.dto.UserDTO;

public interface UserService {
    
    UserDTO save(UserDTO userDTO);

    Optional<UserDTO> update(UserDTO userDTO);

    List<UserDTO> findAll();

    Optional<UserDTO> findOne(Long id);

    Optional<UserDTO> findByLogin(String login);

    void delete(Long id);

    Optional<UserDTO> activateRegistration(String key);

    boolean resetPassword(String key, String newPassword);
}