package com.sena.enter.mapper;

import com.sena.enter.dto.DocumentTypeDTO;
import com.sena.enter.models.DocumentType;

public interface DocumentTypeMapper {

    DocumentType toDocumentType(DocumentTypeDTO dto);

    DocumentTypeDTO toDto(DocumentType documentType);
}
