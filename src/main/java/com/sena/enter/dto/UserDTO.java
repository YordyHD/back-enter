package com.sena.enter.dto;

import java.io.Serializable;
import java.util.Set;
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
    private String firstName;
    private String lastName;
    private String email;
    private boolean activated;
    private String langKey;
    private String imageUrl;
    private String password;
    private Set<String> authorities;

    public UserDTO(User user) {
        this.id = user.getId();
        this.login = user.getLog();
        this.firstName = user.getFName();
        this.lastName = user.getLName();
        this.email = user.getEma();
        this.activated = user.isActivated();
        this.langKey = user.getLangKey();
        this.imageUrl = user.getImageUrl();
        this.authorities = user.getAuthorities().stream()
            .map(authority -> authority.getName())
            .collect(java.util.stream.Collectors.toSet());
    }
}

