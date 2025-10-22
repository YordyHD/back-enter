package com.sena.enter.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "actor_audiovisual_content")
public class ActorAudiovisualContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Size(max = 30)
    @Column(name = "actor_type", length = 30)
    private String actorType;

    @Size(max = 20)
    @Column(name = "ender_character", length = 20)
    private String character;
}
