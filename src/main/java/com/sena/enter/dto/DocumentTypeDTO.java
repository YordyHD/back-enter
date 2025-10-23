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


public class DocumentTypeDTO implements Serializable{

    private Long id;

    @NotNull
    @Size(max = 10)
    private String initials;

    @NotNull
    @Size(max = 100)
    private String documentName;

}
