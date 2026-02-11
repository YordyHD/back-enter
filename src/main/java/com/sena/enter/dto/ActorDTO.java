package com.sena.enter.dto;

import java.io.Serializable;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ActorDTO implements Serializable {

    private Long id;

    @Size(max = 30)
    private String nameActor;

    @Size(max = 30)
    private String lastNameActor;

    private String picture;

    private String pictureContentType;
}
