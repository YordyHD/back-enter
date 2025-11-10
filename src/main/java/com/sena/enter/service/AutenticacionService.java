package com.sena.enter.service;

import com.sena.enter.dto.LoginDto;
import com.sena.enter.dto.RespuestaLoginDto;

public interface AutenticacionService {
    RespuestaLoginDto iniciarSesion(LoginDto login);
}
