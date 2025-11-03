package com.sena.enter.service;

import java.util.List;

import com.sena.enter.dto.BillDTO;

public interface BillService {

    BillDTO save(BillDTO billDTO);

    BillDTO update(BillDTO billDTO);

    BillDTO findOne(Long id);

    List<BillDTO> findAll();

    void delete(Long id);
}
