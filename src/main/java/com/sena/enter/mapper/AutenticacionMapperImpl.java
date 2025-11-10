package com.sena.enter.mapper;

import org.springframework.stereotype.Component;
import com.sena.enter.dto.RespuestaLoginDto;
import com.sena.enter.models.User;

@Component
public class AutenticacionMapperImpl implements AutenticacionMapper{

    @Override
    public RespuestaLoginDto aRespuestaInicioDeSesionDto(User user, String token) {
        RespuestaLoginDto dto = new RespuestaLoginDto();
        dto.setToken(token);
        dto.setNombreCompleto(user.getFName() + " " + user.getLName());
        dto.setRol(user.getAuthorities().stream()
                .findFirst()
                .map(auth -> auth.getName())
                .orElse("USER"));
        return dto;
    }
}



