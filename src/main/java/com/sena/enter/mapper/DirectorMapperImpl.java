package com.sena.enter.mapper;

import org.springframework.stereotype.Component;

import com.sena.enter.dto.DirectorDTO;
import com.sena.enter.models.Director;

@Component
public class DirectorMapperImpl implements DirectorMapper {

    @Override
    public DirectorDTO toDTO(Director director) {
        if (director == null) {
            return null;
        }

        DirectorDTO dto = new DirectorDTO();
        dto.setId(director.getId());
        dto.setNameDirector(director.getNDirector());
        dto.setLasNameDirector(director.getLNDirector());
        dto.setPicture(director.getPic());
        dto.setPictureContentType(director.getPicContentType());
        dto.setYearbirth(director.getYear());

        return dto;
    }

    @Override
    public Director toDirector(DirectorDTO dto) {
        if (dto == null) {
            return null;
        }

        Director director = new Director();
        director.setId(dto.getId());
        director.setNDirector(dto.getNameDirector());
        director.setLNDirector(dto.getLasNameDirector());
        director.setPic(dto.getPicture());
        director.setPicContentType(dto.getPictureContentType());
        director.setYear(dto.getYearbirth());

        return director;
    }
}
