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
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "actor_audiovisual_content")
public class ActorAudiovisualContent implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Size(max = 30)
    @Column(name = "actor_type", length = 30)
    private String actorType;

    @Size(max = 20)
    @Column(name = "ender_character", length = 20)
    private String charac;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = { "views", "actorAudiovisualContents", "filmGenres" }, allowSetters = true)
    private AudiovisualContent audiovisualCont;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = { "actorAudiovisualContents" }, allowSetters = true)
    private Actor actor;
}
