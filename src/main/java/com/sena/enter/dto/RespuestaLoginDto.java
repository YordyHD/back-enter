package com.sena.enter.dto;

import lombok.Data;

@Data
public class RespuestaLoginDto {
    private String token;
    private String nombreCompleto;
    private String rol;
}
