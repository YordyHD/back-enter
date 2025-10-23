package com.sena.enter.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "audiovisual_content")
public class AudiovisualContent implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Size(max = 30)
    @Column(name = "tittle", length = 30)
    private String tittle;

    @Column(name = "relase_date")
    private LocalDate relaseDate;

    @Size(max = 100)
    @Column(name = "description", length = 100)
    private String description;

    @Column(name = "duration")
    private Integer duration;

    @Column(name = "age_rating")
    private Double ageRating;

    @Size(max = 255)
    @Column(name = "trailer_url", length = 255)
    private String trailerURL;

    @Size(max = 30)
    @Column(name = "country_production", length = 30)
    private String countryProduction;

    @Column(name = "rating_promedy")
    private Double ratingPromedy;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "audiovisualContent")
    @JsonIgnoreProperties(value = { "customer", "audiovisualContent" }, allowSetters = true)
    private Set<View> views = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "audiovisualContent")
    @JsonIgnoreProperties(value = { "audiovisualContent", "actor" }, allowSetters = true)
    private Set<ActorAudiovisualContent> actorAudiovisualContents = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @NotNull
    @JoinTable(
        name = "rel_audiovisual_content__film_genre",
        joinColumns = @JoinColumn(name = "audiovisual_content_id"),
        inverseJoinColumns = @JoinColumn(name = "film_genre_id")
    )
    @JsonIgnoreProperties(value = { "audiovisualContents", "customers" }, allowSetters = true)
    private Set<FilmGenre> filmGenres = new HashSet<>();
}
