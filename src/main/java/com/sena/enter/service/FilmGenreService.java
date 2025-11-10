package com.sena.enter.service;

import java.util.List;

import com.sena.enter.dto.FilmGenreDTO;

public interface FilmGenreService {

    FilmGenreDTO save(FilmGenreDTO filmGenreDTO);

    FilmGenreDTO update(FilmGenreDTO filmGenreDTO);

    FilmGenreDTO findOne(Long id);

    List<FilmGenreDTO> findAll();

    void delete(Long id);
}
