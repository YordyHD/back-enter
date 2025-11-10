package com.sena.enter.mapper;

import com.sena.enter.dto.RespuestaLoginDto;
import com.sena.enter.models.User;

public interface AutenticacionMapper {
    RespuestaLoginDto aRespuestaInicioDeSesionDto(User user, String token);
}
