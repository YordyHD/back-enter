package com.sena.enter.service;

import java.util.List;
import com.sena.enter.dto.DirectorDTO;

public interface DirectorService {
    
    DirectorDTO save(DirectorDTO directorDTO);

    DirectorDTO update(DirectorDTO directorDTO);

    List<DirectorDTO> findAll();

    DirectorDTO findOne(Long id);

    void delete(Long id);
}