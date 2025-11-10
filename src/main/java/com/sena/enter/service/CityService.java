package com.sena.enter.service;

import java.util.List;

import com.sena.enter.dto.CityDTO;

public interface CityService {

    CityDTO save(CityDTO cityDTO);

    CityDTO update(CityDTO cityDTO);

    CityDTO findOne(Long id);

    List<CityDTO> findAll();

    void delete(Long id);
}
