package com.sena.enter.mapper;

import org.springframework.stereotype.Component;

import com.sena.enter.dto.BillMembreshipDTO;
import com.sena.enter.dto.BillDTO;
import com.sena.enter.dto.MembreshipDTO;
import com.sena.enter.models.BillMembreship;
import com.sena.enter.models.BillMembreshipId;
import com.sena.enter.models.Bill;
import com.sena.enter.models.Membreship;

@Component
public class BillMembreshipMapperImpl implements BillMembreshipMapper {
    @Override
    public BillMembreshipDTO toDTO(BillMembreship billMembreship) {
      if (billMembreship == null) {
          return null;
      }

      BillMembreshipDTO dto = new BillMembreshipDTO();
      if (billMembreship.getId() != null) {
        dto.setId(billMembreship.getId().getBillId());
      }
      dto.setFinalDate(billMembreship.getFiDate());
      dto.setInicialDate(billMembreship.getIniDate());
      dto.setSalePrice(billMembreship.getSaleP());

      if (billMembreship.getBill() != null) {
        BillDTO b = new BillDTO();
        b.setId(billMembreship.getBill().getId());
        dto.setBill(b);
      }
      if (billMembreship.getMembreship() != null) {
        MembreshipDTO m = new MembreshipDTO();
        m.setId(billMembreship.getMembreship().getId());
        dto.setMembreship(m);
      }

      return dto;
    }

    @Override
    public BillMembreship toBillMembreship(BillMembreshipDTO dto){
      if (dto == null) {
          return null;
      }
      BillMembreship billMembreship = new BillMembreship();

      Long billId = null;
      Long membreshipId = null;
      if (dto.getBill() != null) billId = dto.getBill().getId();
      if (dto.getMembreship() != null) membreshipId = dto.getMembreship().getId();
      if (billId != null || membreshipId != null) {
        BillMembreshipId id = new BillMembreshipId(billId, membreshipId);
        billMembreship.setId(id);
      }

      billMembreship.setFiDate(dto.getFinalDate());
      billMembreship.setIniDate(dto.getInicialDate());
      billMembreship.setSaleP(dto.getSalePrice());

      if (billId != null) {
        Bill b = new Bill();
        b.setId(billId);
        billMembreship.setBill(b);
      }
      if (membreshipId != null) {
        Membreship m = new Membreship();
        m.setId(membreshipId);
        billMembreship.setMembreship(m);
      }

      return billMembreship;
    }
}
