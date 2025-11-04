package com.sena.enter.service;

import java.util.List;

import com.sena.enter.dto.DepartamentDTO;

public interface DepartamentService {

    DepartamentDTO save(DepartamentDTO departamentDTO);

    DepartamentDTO update(DepartamentDTO departamentDTO);

    DepartamentDTO findOne(Long id);

    List<DepartamentDTO> findAll();

    void delete(Long id);
}
