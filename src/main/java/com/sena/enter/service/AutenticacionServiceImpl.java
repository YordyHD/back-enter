package com.sena.enter.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sena.enter.dto.LoginDto;
import com.sena.enter.dto.RespuestaLoginDto;
import com.sena.enter.exception.CredencialesInvalidasException;
import com.sena.enter.mapper.AutenticacionMapper;
import com.sena.enter.models.User;
import com.sena.enter.repository.UserRepository;

@Service
public class AutenticacionServiceImpl implements AutenticacionService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AutenticacionMapper autenticacionMapper;

    public AutenticacionServiceImpl(
            UserRepository userRepository,
            JwtService jwtService,
            PasswordEncoder passwordEncoder,
            AutenticacionMapper autenticacionMapper) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.autenticacionMapper = autenticacionMapper;
    }

    @Override
    public RespuestaLoginDto iniciarSesion(LoginDto login) {
        User user = userRepository.findByLog(login.getUsername())
                .orElseThrow(() -> new CredencialesInvalidasException("Usuario no encontrado"));

        if (!passwordEncoder.matches(login.getPassword(), user.getPassw())) {
            throw new CredencialesInvalidasException("Contraseña incorrecta");
        }

        String token = jwtService.generarToken(user);

        return autenticacionMapper.aRespuestaInicioDeSesionDto(user, token);
    }
}