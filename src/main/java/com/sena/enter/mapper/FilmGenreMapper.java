package com.sena.enter.mapper;

import com.sena.enter.dto.FilmGenreDTO;
import com.sena.enter.models.FilmGenre;

public interface FilmGenreMapper {

    FilmGenreDTO toDTO(FilmGenre filmGenre);

    FilmGenre toFilmGenre(FilmGenreDTO dto);

}
