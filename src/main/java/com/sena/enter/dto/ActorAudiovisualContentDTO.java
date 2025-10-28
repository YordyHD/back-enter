package com.sena.enter.dto;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActorAudiovisualContentDTO implements Serializable{

    @NotNull(message = "El ID del actor no puede ser nulo")
    private Long actorId;
    private String nombreActor; 

    @NotNull(message = "El ID del contenido audiovisual no puede ser nulo")
    private Long audiovisualContentId;
    private String tituloContenido; 

    @Size(max = 30, message = "El tipo de actor no puede superar los 30 caracteres")
    private String actorType;

    @Size(max = 20, message = "El personaje no puede superar los 20 caracteres")
    private String character;

    @NotNull(message = "La fecha de participación es obligatoria")
    private LocalDate fechaParticipacion;

    @Size(max = 500, message = "Las observaciones no pueden superar los 500 caracteres")
    private String observaciones;

    @Column(nullable = false, unique = true)
    private String usuario;

    @Column(nullable = false)
    private String clave;

}