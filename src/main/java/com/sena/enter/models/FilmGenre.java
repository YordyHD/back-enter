package com.sena.enter.models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "film_genre")
public class FilmGenre implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Size(max = 30)
    @Column(name = "movie_genre", length = 30, nullable = false)
    private String movieGe;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "filmGenres")
    @JsonIgnoreProperties(value = { "views", "actorAudiovisualContents", "filmGenres" }, allowSetters = true)
    private Set<AudiovisualContent> audiovisualContents = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "filmGenres")
    @JsonIgnoreProperties(value = { "user", "bills", "views", "filmGenres", "documentType", "sex", "cities" }, allowSetters = true)
    private Set<Customer> customers = new HashSet<>();
}
