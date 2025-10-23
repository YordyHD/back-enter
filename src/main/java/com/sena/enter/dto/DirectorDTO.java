package com.sena.enter.dto;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Lob;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class DirectorDTO implements Serializable{

    private Long id;

    @Size(max = 30)
    private String nameDirector;

    @Size(max = 30)
    private String lasNameDirector;

    @Lob
    private byte[] picture;

    private String pictureContentType;

    private LocalDate yearbirth;
}
