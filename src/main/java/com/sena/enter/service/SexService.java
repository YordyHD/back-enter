package com.sena.enter.service;

import java.util.List;

import com.sena.enter.dto.SexDTO;

public interface SexService {

    SexDTO save(SexDTO sexDTO);

    SexDTO update(SexDTO sexDTO);

    SexDTO findOne(Long id);

    List<SexDTO> findAll();

    void delete(Long id);
}
