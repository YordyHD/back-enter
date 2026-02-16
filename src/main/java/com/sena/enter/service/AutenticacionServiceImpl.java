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
    private final com.sena.enter.repository.CustomerRepository customerRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AutenticacionMapper autenticacionMapper;

    public AutenticacionServiceImpl(
            UserRepository userRepository,
            com.sena.enter.repository.CustomerRepository customerRepository,
            JwtService jwtService,
            PasswordEncoder passwordEncoder,
            AutenticacionMapper autenticacionMapper) {
        this.userRepository = userRepository;
        this.customerRepository = customerRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.autenticacionMapper = autenticacionMapper;
    }

    @Override
    public RespuestaLoginDto iniciarSesion(LoginDto login) {
        User user = userRepository.findOneByLogin(login.getUsername())
                .orElseThrow(() -> new CredencialesInvalidasException("Usuario no encontrado"));

        if (!passwordEncoder.matches(login.getPassword(), user.getPassword())) {
            throw new CredencialesInvalidasException("Contraseña incorrecta");
        }

        String fullName = customerRepository.findByUser(user)
                .map(c -> c.getFirstName() + (c.getFirstLastName() != null ? " " + c.getFirstLastName() : ""))
                .orElse(user.getLogin());

        String token = jwtService.generarToken(user, fullName);

        return autenticacionMapper.aRespuestaInicioDeSesionDto(user, token, fullName);
    }
}