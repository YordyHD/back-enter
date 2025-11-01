package com.sena.enter.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sena.enter.dto.BillMembreshipDTO;
import com.sena.enter.dto.MembreshipDTO;
import com.sena.enter.models.BillMembreship;
import com.sena.enter.models.Membreship;

import java.util.HashSet;
import java.util.Set;

@Component
public class MembreshipMapperImpl implements MembreshipMapper {

    @Autowired
    private BillMembreshipMapper billMembreshipMapper;

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

        if (membreship.getBillMembreships() != null) {
            Set<BillMembreshipDTO> set = new HashSet<>();
            for (BillMembreship bm : membreship.getBillMembreships()) {
                BillMembreshipDTO bmDto = billMembreshipMapper.toDTO(bm);
                set.add(bmDto);
            }
            dto.setBillMembreships(set);
        }

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

        if (dto.getBillMembreships() != null) {
            Set<BillMembreship> set = new HashSet<>();
            for (BillMembreshipDTO bmDto : dto.getBillMembreships()) {
                BillMembreship bm = billMembreshipMapper.toBillMembreship(bmDto);
                if (bm != null) {
                    bm.setMembreship(membreship);
                    set.add(bm);
                }
            }
            membreship.setBillMembreships(set);
        }

        return membreship;
    }
}
