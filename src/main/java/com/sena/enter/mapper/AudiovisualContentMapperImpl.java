package com.sena.enter.mapper;

import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

import com.sena.enter.dto.AudiovisualContentDTO;
import com.sena.enter.dto.FilmGenreDTO;
import com.sena.enter.models.AudiovisualContent;
import com.sena.enter.models.FilmGenre;

@Component
public class AudiovisualContentMapperImpl implements AudiovisualContentMapper{

  @Override
  public AudiovisualContentDTO toDTO(AudiovisualContent audiovisualContent){
    if (audiovisualContent == null){
      return null;
    }
    AudiovisualContentDTO dto = new AudiovisualContentDTO();
    dto.setId(audiovisualContent.getId());
    dto.setTittle(audiovisualContent.getTit());
    dto.setRelaseDate(audiovisualContent.getRelaDate());
    dto.setDescription(audiovisualContent.getDescrip());
    dto.setDuration(audiovisualContent.getDurat());
    dto.setAgeRating(audiovisualContent.getAgeRati());
    dto.setTrailerURL(audiovisualContent.getTrailerURL());
    dto.setCountryProduction(audiovisualContent.getCountryProduc());
    dto.setRatingPromedy(audiovisualContent.getRatPromedy());
    if (audiovisualContent.getFilmGenres() != null) {
      Set<FilmGenreDTO> fgDtos = new HashSet<>();
      for (FilmGenre fg : audiovisualContent.getFilmGenres()) {
        if (fg == null) continue;
        FilmGenreDTO fgDto = new FilmGenreDTO();
        fgDto.setId(fg.getId());
        fgDto.setMovieGenre(fg.getMovieGe());
        fgDtos.add(fgDto);
      }
      dto.setFilmGenres(fgDtos);
    }
    return dto;
  }

  @Override
public AudiovisualContent toAudiovisualContent(AudiovisualContentDTO dto) {
    if (dto == null) {
        return null;
    }

    AudiovisualContent audiovisualContent = new AudiovisualContent();
    audiovisualContent.setId(dto.getId());
    audiovisualContent.setTit(dto.getTittle());
    audiovisualContent.setRelaDate(dto.getRelaseDate());
    audiovisualContent.setDescrip(dto.getDescription());
    audiovisualContent.setDurat(dto.getDuration());
    audiovisualContent.setAgeRati(dto.getAgeRating());
    audiovisualContent.setTrailerURL(dto.getTrailerURL());
    audiovisualContent.setCountryProduc(dto.getCountryProduction());
    audiovisualContent.setRatPromedy(dto.getRatingPromedy());
    if (dto.getFilmGenres() != null) {
      Set<FilmGenre> fgs = new HashSet<>();
      for (FilmGenreDTO fgDto : dto.getFilmGenres()) {
        if (fgDto == null) continue;
        FilmGenre fg = new FilmGenre();
        fg.setId(fgDto.getId());
        fg.setMovieGe(fgDto.getMovieGenre());
        fgs.add(fg);
      }
      audiovisualContent.setFilmGenres(fgs);
    }
    
    return audiovisualContent;
}
}
