package com.sena.enter.models;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "actor_audiovisual_content")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActorAudiovisualContent implements Serializable {

    @EmbeddedId
    private ActorAudiovisualContentId id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("actorId")
    @JoinColumn(name = "actor_id")
    @NotNull
    @JsonIgnoreProperties(value = { "actorAudiovisualContents" }, allowSetters = true)
    private Actor actor;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("audiovisualContentId")
    @JoinColumn(name = "audiovisual_content_id")
    @NotNull
    @JsonIgnoreProperties(value = { "views", "actorAudiovisualContents", "filmGenres" }, allowSetters = true)
    private AudiovisualContent audiovisualContent;

    @Column(name = "actor_type", length = 30)
    private String actorType;

    @Column(name = "ender_character", length = 20)
    private String charac;

    @Column(name = "orden_aparicion")
    private Integer ordenAparicion;
}