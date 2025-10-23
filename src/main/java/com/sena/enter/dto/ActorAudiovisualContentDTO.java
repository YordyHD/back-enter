package com.sena.enter.dto;

import java.io.Serializable;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor


public class ActorAudiovisualContentDTO implements Serializable{

    private Long id;

    @Size(max = 30)
    private String actorType;

    @Size(max = 20)
    private String character;

    @NotNull
    private AudiovisualContentDTO audiovisualContent;

    @NotNull
    private ActorDTO actor;
}