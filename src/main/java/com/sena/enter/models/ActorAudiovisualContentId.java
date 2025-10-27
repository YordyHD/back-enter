package com.sena.enter.models;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActorAudiovisualContentId implements Serializable {
    private Long actorId;
    private Long audiovisualContentId;
};
