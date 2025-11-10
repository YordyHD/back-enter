package com.sena.enter.service;

import java.util.List;

import com.sena.enter.dto.MembreshipDTO;

public interface MembreshipService {

    MembreshipDTO save(MembreshipDTO membreshipDTO);

    MembreshipDTO update(MembreshipDTO membreshipDTO);

    MembreshipDTO findOne(Long id);

    List<MembreshipDTO> findAll();

    void delete(Long id);
}
