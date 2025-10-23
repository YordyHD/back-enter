package com.sena.enter.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CustomerDTO implements Serializable{

        private Long id;

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
    private String firstLasName;

    @Size(max = 50)
    private String secondLastName;

    @NotNull
    private UserDTO user;

    @NotNull
    private Set<FilmGenreDTO> filmGenres = new HashSet<>();

    @NotNull
    private DocumentTypeDTO documentType;

    @NotNull
    private SexDTO sex;

    @NotNull
    private CityDTO cities;
}
