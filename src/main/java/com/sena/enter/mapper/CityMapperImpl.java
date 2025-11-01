package com.sena.enter.mapper;

import org.springframework.stereotype.Component;

import com.sena.enter.dto.CityDTO;
import com.sena.enter.dto.DepartamentDTO;
import com.sena.enter.models.City;
import com.sena.enter.models.Departament;

@Component
public class CityMapperImpl implements CityMapper{
    public CityDTO toDto(City city) {
        if (city == null) {
            return null;
        }

        CityDTO dto = new CityDTO();
        dto.setId(city.getId());
        dto.setName(city.getN());
        if (city.getDepartament() != null) {
            DepartamentDTO d = new DepartamentDTO();
            d.setId(city.getDepartament().getId());
            d.setDepartamentName(city.getDepartament().getDepartName());
            dto.setDepartament(d);
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
        if (dto.getDepartament() != null) {
            Departament dep = new Departament();
            dep.setId(dto.getDepartament().getId());
            dep.setDepartName(dto.getDepartament().getDepartamentName());
            city.setDepartament(dep);
        }

        return city;
    }
}
