package com.sena.enter.models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

@Entity
@Table (name = "actor")
public class Actor implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Size(max = 30)
    @Column(name = "name_actor", length = 30)
    private String nameActor;

    @Size(max = 30)
    @Column(name = "last_name_actor", length = 30)
    private String lastNameActor;

    @Lob
    @Column(name = "picture")
    private byte[] picture;

    @Column(name = "picture_content_type")
    private String pictureContentType;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "actor")
    @JsonIgnoreProperties(value = { "audiovisualContent", "actor" }, allowSetters = true)
    private Set<ActorAudiovisualContent> actorAudiovisualContents = new HashSet<>();
}
