package com.sena.enter.mapper;

import com.sena.enter.dto.MembreshipDTO;
import com.sena.enter.models.Membreship;

public interface MembreshipMapper {

    MembreshipDTO toDTO(Membreship membreship);

    Membreship toMembreship(MembreshipDTO dto);
}
