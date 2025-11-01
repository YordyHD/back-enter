package com.sena.enter.mapper;

import org.springframework.stereotype.Component;

import com.sena.enter.dto.DepartamentDTO;
import com.sena.enter.models.Departament;

@Component
public class DepartamentMapperImpl {

    public DepartamentDTO toDto(Departament departament) {
        if (departament == null) {
            return null;
        }

        DepartamentDTO departamentDTO = new DepartamentDTO();
        departamentDTO.setId(departament.getId());
        departamentDTO.setDepartamentName(departament.getDepartName());

        return departamentDTO;
    }

    public Departament toDepartament(DepartamentDTO departamentDTO) {
        if (departamentDTO == null) {
            return null;
        }

        Departament departament = new Departament();
        departament.setId(departamentDTO.getId());
        departament.setDepartName(departamentDTO.getDepartamentName());

        return departament;
    }
}
