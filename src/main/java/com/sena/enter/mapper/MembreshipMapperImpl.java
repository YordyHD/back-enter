package com.sena.enter.mapper;

import org.springframework.stereotype.Component;
import com.sena.enter.dto.MembreshipDTO;
import com.sena.enter.models.Membreship;

@Component
public class MembreshipMapperImpl implements MembreshipMapper {

    @Override
    public MembreshipDTO toDTO(Membreship membreship) {
        if (membreship == null) {
            return null;
        }

        MembreshipDTO dto = new MembreshipDTO();
        dto.setId(membreship.getId());
        dto.setMembershipName(membreship.getMembershipN());
        dto.setPrice(membreship.getPri());
        dto.setDuration(membreship.getDurat());
        dto.setImagen(membreship.getIma());
        dto.setImagenContentType(membreship.getImaContentType());
        dto.setDescription(membreship.getDescrip());
        return dto;
    }

    @Override
    public Membreship toMembreship(MembreshipDTO dto) {
        if (dto == null) {
            return null;
        }

        Membreship membreship = new Membreship();
        membreship.setId(dto.getId());
        membreship.setMembershipN(dto.getMembershipName());
        membreship.setPri(dto.getPrice());
        membreship.setDurat(dto.getDuration());
        membreship.setIma(dto.getImagen());
        membreship.setImaContentType(dto.getImagenContentType());
        membreship.setDescrip(dto.getDescription());
        return membreship;
    }
}