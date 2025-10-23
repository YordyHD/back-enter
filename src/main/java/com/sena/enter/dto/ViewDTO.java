package com.sena.enter.dto;

import java.io.Serializable;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ViewDTO implements Serializable{

    private Long id;

    private Integer timeSeen;

    private Integer duration;

    @NotNull
    private CustomerDTO customer;

    @NotNull
    private AudiovisualContentDTO audiovisualContent;
}
