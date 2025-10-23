package com.sena.enter.dto;

import java.io.Serializable;
import com.sena.enter.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UserDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String login;

    public UserDTO(User user) {
        this.id = user.getId();
        this.login = user.getLog();
    }
}

