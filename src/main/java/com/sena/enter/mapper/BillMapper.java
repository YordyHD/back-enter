package com.sena.enter.mapper;

import com.sena.enter.dto.BillDTO;
import com.sena.enter.models.Bill;

public interface BillMapper {

  Bill toBill(BillDTO dto);

  BillDTO toDTO(Bill bill);
}
