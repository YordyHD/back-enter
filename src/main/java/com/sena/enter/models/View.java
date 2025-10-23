package com.sena.enter.models;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "view")

public class View implements Serializable{

        @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "time_seen")
    private Integer tiSeen;

    @Column(name = "duration")
    private Integer durat;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = { "user", "bills", "views", "filmGenres", "documentType", "sex", "cities" }, allowSetters = true)
    private Customer custo;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = { "views", "actorAudiovisualContents", "filmGenres" }, allowSetters = true)
    private AudiovisualContent audiovisualContent;
}
