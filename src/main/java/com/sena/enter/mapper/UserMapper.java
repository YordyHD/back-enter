package com.sena.enter.mapper;

import com.sena.enter.dto.UserDTO;
import com.sena.enter.models.User;

public interface UserMapper {

    UserDTO toDTO(User user);

    User toUser(UserDTO dto);

    User updateUserFromDto(User userToUpdate, UserDTO dto);
}
