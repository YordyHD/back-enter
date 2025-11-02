package com.sena.enter.mapper;

import com.sena.enter.dto.ViewDTO;
import com.sena.enter.models.View;

public interface ViewMapper {

    ViewDTO toDTO(View view);

    View toEntity(ViewDTO dto);

    View updateViewFromDto(View view, ViewDTO dto);
}