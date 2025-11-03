package com.sena.enter.service;

import org.springframework.stereotype.Service;

import com.sena.enter.dto.FilmGenreDTO;
import com.sena.enter.mapper.FilmGenreMapper;
import com.sena.enter.models.FilmGenre;
import com.sena.enter.repository.FilmGenreRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class FilmGenreServiceImpl implements FilmGenreService {

    private final FilmGenreRepository filmGenreRepository;
    private final FilmGenreMapper filmGenreMapper;

    public FilmGenreServiceImpl(FilmGenreRepository filmGenreRepository, FilmGenreMapper filmGenreMapper) {
        this.filmGenreRepository = filmGenreRepository;
        this.filmGenreMapper = filmGenreMapper;
    }

    @Override
    public FilmGenreDTO save(FilmGenreDTO filmGenreDTO) {
        if (filmGenreDTO == null) {
            return null;
        }
        FilmGenre filmGenre = filmGenreMapper.toFilmGenre(filmGenreDTO);
        if (filmGenre == null) {
            return null;
        }
        filmGenre = filmGenreRepository.save(filmGenre);
        return filmGenreMapper.toDTO(filmGenre);
    }

    @Override
    public FilmGenreDTO update(FilmGenreDTO filmGenreDTO) {
        if (filmGenreDTO == null || filmGenreDTO.getId() == null) {
            return null;
        }
        FilmGenre filmGenre = filmGenreMapper.toFilmGenre(filmGenreDTO);
        filmGenre = filmGenreRepository.save(filmGenre);
        return filmGenreMapper.toDTO(filmGenre);
    }

    @Override
    public FilmGenreDTO findOne(Long id) {
        if (id == null) {
            return null;
        }
        return filmGenreRepository.findById(id)
                .map(filmGenreMapper::toDTO)
                .orElse(null);
    }

    @Override
    public java.util.List<FilmGenreDTO> findAll() {
        return filmGenreRepository.findAll().stream()
                .map(filmGenreMapper::toDTO)
                .toList();
    }

    @Override
    public void delete(Long id) {
        if (id != null && filmGenreRepository.existsById(id)) {
            filmGenreRepository.deleteById(id);
        }
    }
}
