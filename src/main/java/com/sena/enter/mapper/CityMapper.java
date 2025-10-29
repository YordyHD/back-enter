package com.sena.enter.mapper;

import com.sena.enter.dto.CityDTO;
import com.sena.enter.models.City;

public interface CityMapper {

    CityDTO toDto(City city);

    City toCity(CityDTO dto);
}
