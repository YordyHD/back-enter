package com.sena.enter.mapper;

import com.sena.enter.dto.BillMembreshipDTO;
import com.sena.enter.models.BillMembreship;

public interface BillMembreshipMapper {

    BillMembreshipDTO toDTO(BillMembreship billMembreship);

    BillMembreship toBillMembreship(BillMembreshipDTO dto);
}
