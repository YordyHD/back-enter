package com.sena.enter.service;

import java.util.List;

import com.sena.enter.dto.DocumentTypeDTO;

public interface DocumentTypeService {

    DocumentTypeDTO create(DocumentTypeDTO documentTypeDTO);

    DocumentTypeDTO update(DocumentTypeDTO documentTypeDTO);

    DocumentTypeDTO findOne(Long id);

    List<DocumentTypeDTO> findAll();

    void delete(Long id);

}
