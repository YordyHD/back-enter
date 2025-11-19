package com.sena.enter.mapper;

import org.springframework.stereotype.Component;



import com.sena.enter.dto.FilmGenreDTO;
import com.sena.enter.models.FilmGenre;

@Component
public class FilmGenreMapperImpl implements FilmGenreMapper {

    @Override
    public FilmGenreDTO toDTO(FilmGenre filmGenre) {
        if (filmGenre == null) {
            return null;
        }

        FilmGenreDTO dto = new FilmGenreDTO();
        dto.setId(filmGenre.getId());
        dto.setMovieGenre(filmGenre.getMovieGe());


        return dto;
    }

    @Override
    public FilmGenre toFilmGenre(FilmGenreDTO dto) {
        if (dto == null) {
            return null;
        }

    FilmGenre filmGenre = new FilmGenre();
    filmGenre.setId(dto.getId());
    filmGenre.setMovieGe(dto.getMovieGenre());


    return filmGenre;
    }
}
