package com.sena.enter.mapper;

import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

import com.sena.enter.dto.AudiovisualContentDTO;
import com.sena.enter.dto.CustomerDTO;
import com.sena.enter.dto.FilmGenreDTO;
import com.sena.enter.models.AudiovisualContent;
import com.sena.enter.models.Customer;
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

        if (filmGenre.getAudiovisualContents() != null) {
            Set<AudiovisualContentDTO> acDtos = new HashSet<>();
            for (AudiovisualContent ac : filmGenre.getAudiovisualContents()) {
                if (ac == null) continue;
                AudiovisualContentDTO acDto = new AudiovisualContentDTO();
                acDto.setId(ac.getId());
                acDto.setTittle(ac.getTit());
                acDtos.add(acDto);
            }
            dto.setAudiovisualContents(acDtos);
        }

        if (filmGenre.getCustomers() != null) {
            Set<CustomerDTO> custDtos = new HashSet<>();
            for (Customer c : filmGenre.getCustomers()) {
                if (c == null) continue;
                CustomerDTO cDto = new CustomerDTO();
                cDto.setId(c.getId());
                custDtos.add(cDto);
            }
            dto.setCustomers(custDtos);
        }

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

        if (dto.getAudiovisualContents() != null) {
            Set<AudiovisualContent> acs = new HashSet<>();
            for (AudiovisualContentDTO acDto : dto.getAudiovisualContents()) {
                if (acDto == null) continue;
                AudiovisualContent ac = new AudiovisualContent();
                ac.setId(acDto.getId());
                ac.setTit(acDto.getTittle());
                acs.add(ac);
            }
            filmGenre.setAudiovisualContents(acs);
        }

        if (dto.getCustomers() != null) {
            Set<Customer> custs = new HashSet<>();
            for (CustomerDTO cDto : dto.getCustomers()) {
                if (cDto == null) continue;
                Customer c = new Customer();
                c.setId(cDto.getId());
                custs.add(c);
            }
            filmGenre.setCustomers(custs);
        }

    return filmGenre;
    }
}
