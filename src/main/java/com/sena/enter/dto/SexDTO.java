package com.sena.enter.dto;

import java.io.Serializable;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor

public class SexDTO implements Serializable{

    private Long id;

    @Size(max = 40)
    private String sexName;
}
