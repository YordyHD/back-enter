package com.sena.enter.dto;

import java.io.Serializable;
import java.time.LocalDate;
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

public class AudiovisualContentDTO implements Serializable{

        private Long id;

    @Size(max = 30)
    private String tittle;

    private LocalDate relaseDate;

    @Size(max = 100)
    private String description;

    private Integer duration;

    private Double ageRating;

    @Size(max = 255)
    private String trailerURL;

    @Size(max = 30)
    private String countryProduction;

    private Double ratingPromedy;

    @NotNull
    private Set<FilmGenreDTO> filmGenres = new HashSet<>();

}
