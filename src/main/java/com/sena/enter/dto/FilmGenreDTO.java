package com.sena.enter.dto;

import java.io.Serializable;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class FilmGenreDTO implements Serializable{

    private Long id;

    @NotNull
    @Size(max = 30)
    private String movieGenre;

}
