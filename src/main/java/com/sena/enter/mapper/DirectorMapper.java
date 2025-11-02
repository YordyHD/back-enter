package com.sena.enter.mapper;

import com.sena.enter.dto.DirectorDTO;
import com.sena.enter.models.Director;

public interface DirectorMapper {

    DirectorDTO toDTO(Director director);

    Director toDirector(DirectorDTO dto);

}
