package com.sena.enter.mapper;

import org.springframework.stereotype.Component;
import com.sena.enter.dto.MembreshipDTO;
import com.sena.enter.models.Membreship;

@Component
public interface MembreshipMapper {

    MembreshipDTO toDTO(Membreship membreship);

    Membreship toMembreship(MembreshipDTO dto);
}