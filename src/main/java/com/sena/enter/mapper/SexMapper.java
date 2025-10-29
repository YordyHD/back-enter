package com.sena.enter.mapper;

import com.sena.enter.dto.SexDTO;
import com.sena.enter.models.Sex;

public interface SexMapper {

    Sex toSex(SexDTO dto);

    SexDTO toDTO(Sex sex);

}
