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

public class CityDTO implements Serializable{

        private Long id;

    @NotNull
    @Size(max = 200)
    private String name;

    @NotNull
    private DepartamentDTO departament;
}
