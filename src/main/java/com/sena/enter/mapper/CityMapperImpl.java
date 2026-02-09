package com.sena.enter.mapper;

import org.springframework.stereotype.Component;

import com.sena.enter.dto.CityDTO;
import com.sena.enter.models.City;
import com.sena.enter.models.Departament;

@Component
public class CityMapperImpl implements CityMapper {
    public CityDTO toDto(City city) {
        if (city == null) {
            return null;
        }

        CityDTO dto = new CityDTO();
        dto.setId(city.getId());
        dto.setName(city.getN());
        if (city.getDepartament() != null) {
            dto.setDepartamentId(city.getDepartament().getId());
            dto.setDepartamentName(city.getDepartament().getDepartName());
        }

        return dto;
    }

    public City toCity(CityDTO dto) {
        if (dto == null) {
            return null;
        }

        City city = new City();
        city.setId(dto.getId());
        city.setN(dto.getName());
        if (dto.getDepartamentId() != null) {
            Departament departament = new Departament();
            departament.setId(dto.getDepartamentId());
            city.setDepartament(departament);
        }

        return city;
    }
}
