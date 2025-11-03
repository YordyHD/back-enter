package com.sena.enter.mapper;

import com.sena.enter.dto.DepartamentDTO;
import com.sena.enter.models.Departament;

public interface DepartamentMapper {

    DepartamentDTO toDto(Departament departament);

    Departament toDepartament(DepartamentDTO dto);
}