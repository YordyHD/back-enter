package com.sena.enter.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class MembreshipDTO implements Serializable{

    private Long id;

    @NotNull
    @Size(max = 255)
    private String membershipName;

    private BigDecimal price;

    private Integer duration;

    @Lob
    private byte[] imagen;

    private String imagenContentType;

    @Size(max = 255)
    private String description;

}
