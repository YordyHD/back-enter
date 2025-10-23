package com.sena.enter.dto;

import java.io.Serializable;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class SexDTO implements Serializable{

    private Long id;

    @Size(max = 40)
    private String sexName;
}
