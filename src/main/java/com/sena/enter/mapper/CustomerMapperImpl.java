package com.sena.enter.mapper;

import org.springframework.stereotype.Component;

import com.sena.enter.dto.CustomerDTO;
import com.sena.enter.dto.DocumentTypeDTO;
import com.sena.enter.dto.FilmGenreDTO;
import com.sena.enter.dto.SexDTO;
import com.sena.enter.dto.UserDTO;
import com.sena.enter.dto.CityDTO;
import com.sena.enter.models.Customer;
import com.sena.enter.models.DocumentType;
import com.sena.enter.models.FilmGenre;
import com.sena.enter.models.Sex;
import com.sena.enter.models.User;
import com.sena.enter.models.City;

import java.util.HashSet;
import java.util.Set;

@Component
public class CustomerMapperImpl implements CustomerMapper {

    @Override
    public CustomerDTO toDTO(Customer customer) {
        if (customer == null) return null;

        CustomerDTO dto = new CustomerDTO();
        dto.setId(customer.getId());
        dto.setDocumentNumber(customer.getDocumentNum());
        dto.setFirstName(customer.getFName());
        dto.setSecondName(customer.getSName());
        dto.setFirstLasName(customer.getFLName());
        dto.setSecondLastName(customer.getSLName());

        if (customer.getUser() != null) {
            dto.setUser(new UserDTO(customer.getUser()));
        }

        if (customer.getFilmGenres() != null) {
            Set<FilmGenreDTO> fgDtos = new HashSet<>();
            for (FilmGenre fg : customer.getFilmGenres()) {
                if (fg == null) continue;
                FilmGenreDTO fgDto = new FilmGenreDTO();
                fgDto.setId(fg.getId());
                fgDto.setMovieGenre(fg.getMovieGe());
                fgDtos.add(fgDto);
            }
            dto.setFilmGenres(fgDtos);
        }

        if (customer.getDocumentType() != null) {
            DocumentType dt = customer.getDocumentType();
            DocumentTypeDTO dtDto = new DocumentTypeDTO();
            dtDto.setId(dt.getId());
            dtDto.setInitials(dt.getIniti());
            dtDto.setDocumentName(dt.getDocumentN());
            dto.setDocumentType(dtDto);
        }

        if (customer.getSex() != null) {
            Sex s = customer.getSex();
            SexDTO sDto = new SexDTO();
            sDto.setId(s.getId());
            sDto.setSexName(s.getSexN());
            dto.setSex(sDto);
        }

        if (customer.getCities() != null) {
            City c = customer.getCities();
            CityDTO cDto = new CityDTO();
            cDto.setId(c.getId());
            cDto.setName(c.getN());
            dto.setCities(cDto);
        }

        return dto;
    }

    @Override
    public Customer toCustomer(CustomerDTO dto) {
        if (dto == null) return null;

        Customer customer = new Customer();
        customer.setId(dto.getId());
        customer.setDocumentNum(dto.getDocumentNumber());
        customer.setFName(dto.getFirstName());
        customer.setSName(dto.getSecondName());
        customer.setFLName(dto.getFirstLasName());
        customer.setSLName(dto.getSecondLastName());

        if (dto.getUser() != null) {
            User u = new User();
            u.setId(dto.getUser().getId());
            customer.setUser(u);
        }

        if (dto.getFilmGenres() != null) {
            Set<FilmGenre> fgs = new HashSet<>();
            for (FilmGenreDTO fgDto : dto.getFilmGenres()) {
                if (fgDto == null) continue;
                FilmGenre fg = new FilmGenre();
                fg.setId(fgDto.getId());
                fg.setMovieGe(fgDto.getMovieGenre());
                fgs.add(fg);
            }
            customer.setFilmGenres(fgs);
        }

        if (dto.getDocumentType() != null) {
            DocumentType dt = new DocumentType();
            dt.setId(dto.getDocumentType().getId());
            dt.setIniti(dto.getDocumentType().getInitials());
            dt.setDocumentN(dto.getDocumentType().getDocumentName());
            customer.setDocumentType(dt);
        }

        if (dto.getSex() != null) {
            Sex s = new Sex();
            s.setId(dto.getSex().getId());
            customer.setSex(s);
        }

        if (dto.getCities() != null) {
            City c = new City();
            c.setId(dto.getCities().getId());
            customer.setCities(c);
        }

        return customer;
    }
}
