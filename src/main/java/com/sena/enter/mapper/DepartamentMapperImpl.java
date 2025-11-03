package com.sena.enter.mapper;

import org.springframework.stereotype.Component;
import com.sena.enter.dto.DepartamentDTO;
import com.sena.enter.models.Departament;

@Component
public class DepartamentMapperImpl implements DepartamentMapper {

    @Override
    public DepartamentDTO toDto(Departament departament) {
        if (departament == null) {
            return null;
        }

        DepartamentDTO dto = new DepartamentDTO();
        dto.setId(departament.getId());
        dto.setDepartamentName(departament.getDepartName());
        return dto;
    }

    @Override
    public Departament toDepartament(DepartamentDTO dto) {
        if (dto == null) {
            return null;
        }

        Departament departament = new Departament();
        departament.setId(dto.getId());
        departament.setDepartName(dto.getDepartamentName());
        return departament;
    }
}