package com.sena.enter.mapper;

import com.sena.enter.dto.UserDTO;
import com.sena.enter.models.User;

public interface UserMapper {

    UserDTO toDTO(User user);

    User toUser(UserDTO dto);

    /**
     * Update a User entity using data from DTO.
     * Only non-null fields from the DTO will be used to update the entity.
     * @param userToUpdate the entity to update
     * @param dto the DTO containing the data
     * @return the updated entity
     */
    User updateUserFromDto(User userToUpdate, UserDTO dto);
}
