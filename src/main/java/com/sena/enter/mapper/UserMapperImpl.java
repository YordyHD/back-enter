package com.sena.enter.mapper;

import java.util.HashSet;
import java.util.Set;
import org.springframework.stereotype.Component;

import com.sena.enter.dto.UserDTO;
import com.sena.enter.models.User;
import com.sena.enter.models.Authority;

@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDTO toDTO(User user) {
        if (user == null) return null;
        if (user.getLog() == null) {
            throw new IllegalArgumentException("User login cannot be null");
        }

        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setLogin(user.getLog());
        dto.setFirstName(user.getFName());
        dto.setLastName(user.getLName());
        dto.setEmail(user.getEma());
        dto.setActivated(user.isActivated());
        dto.setLangKey(user.getLangKey());
        dto.setImageUrl(user.getImageUrl());
        dto.setAuthorities(user.getAuthorities().stream()
            .map(Authority::getName)
            .collect(java.util.stream.Collectors.toSet()));
        return dto;
    }

    @Override
    public User toUser(UserDTO dto) {
        if (dto == null) return null;

        User user = new User();
        user.setId(dto.getId());
        user.setLog(dto.getLogin());
        user.setFName(dto.getFirstName());
        user.setLName(dto.getLastName());
        user.setEma(dto.getEmail());
        user.setActivated(dto.isActivated());
        user.setLangKey(dto.getLangKey());
        user.setImageUrl(dto.getImageUrl());
        Set<Authority> authorities = new HashSet<>();
        if (dto.getAuthorities() != null) {
            authorities = dto.getAuthorities().stream()
                .map(authorityName -> {
                    Authority authority = new Authority();
                    authority.setName(authorityName);
                    return authority;
                })
                .collect(java.util.stream.Collectors.toSet());
        }
        user.setAuthorities(authorities);
        return user;
    }

    @Override
    public User updateUserFromDto(User userToUpdate, UserDTO dto) {
        if (userToUpdate == null || dto == null) return userToUpdate;

        if (dto.getLogin() != null) {
            userToUpdate.setLog(dto.getLogin());
        }
        if (dto.getFirstName() != null) {
            userToUpdate.setFName(dto.getFirstName());
        }
        if (dto.getLastName() != null) {
            userToUpdate.setLName(dto.getLastName());
        }
        if (dto.getEmail() != null) {
            userToUpdate.setEma(dto.getEmail());
        }
        userToUpdate.setActivated(dto.isActivated());
        if (dto.getLangKey() != null) {
            userToUpdate.setLangKey(dto.getLangKey());
        }
        if (dto.getImageUrl() != null) {
            userToUpdate.setImageUrl(dto.getImageUrl());
        }
        if (dto.getAuthorities() != null) {
            Set<Authority> authorities = dto.getAuthorities().stream()
                .map(authorityName -> {
                    Authority authority = new Authority();
                    authority.setName(authorityName);
                    return authority;
                })
                .collect(java.util.stream.Collectors.toSet());
            userToUpdate.setAuthorities(authorities);
        }
        return userToUpdate;
    }
}
