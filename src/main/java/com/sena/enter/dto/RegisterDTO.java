package com.sena.enter.dto;

import java.io.Serializable;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(min = 1, max = 50)
    private String username;

    @Email
    @Size(min = 5, max = 254)
    @NotNull
    private String email;

    @NotNull
    @Size(min = 4, max = 100)
    private String password;

    @NotNull
    @Size(max = 50)
    private String documentNumber;

    @NotNull
    @Size(max = 50)
    private String firstName;

    @Size(max = 50)
    private String secondName;

    @NotNull
    @Size(max = 50)
    private String firstLastName;

    @Size(max = 50)
    private String secondLastName;

    @NotNull
    private Long documentTypeId;

    @NotNull
    private Long sexId;

    @NotNull
    private Long cityId;
}
