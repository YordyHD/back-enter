package com.sena.enter.models;


import java.io.Serializable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "actor_audiovisual_content")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActorAudiovisualContent implements Serializable{

    @EmbeddedId
    private ActorAudiovisualContentId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("actorId")
    @JoinColumn(name = "actor_id")
    private Actor actor;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("audiovisualContentId")
    @JoinColumn(name = "audiovisual_content_id")
    private AudiovisualContent audiovisualContent;

    @Column(name = "actor_type", length = 30)
    private String actorType;

    @Column(name = "ender_character", length = 20)
    private Integer ordenAparicion;
}
