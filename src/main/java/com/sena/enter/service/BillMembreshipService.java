package com.sena.enter.service;

import java.util.List;
import com.sena.enter.dto.BillMembreshipDTO;

public interface BillMembreshipService {
    
    BillMembreshipDTO save(BillMembreshipDTO billMembreshipDTO);

    BillMembreshipDTO update(BillMembreshipDTO billMembreshipDTO);

    List<BillMembreshipDTO> findAll();

    BillMembreshipDTO findOne(Long billId, Long membreshipId);

    void delete(Long billId, Long membreshipId);
}