package com.sena.enter.mapper;

import org.springframework.stereotype.Component;

import com.sena.enter.dto.DocumentTypeDTO;
import com.sena.enter.models.DocumentType;

@Component
public class DocumentTypeMapperImpl implements DocumentTypeMapper {

    @Override
    public DocumentTypeDTO toDto(DocumentType documentType) {
        if (documentType == null) {
            return null;
        }
        
        DocumentTypeDTO dto = new DocumentTypeDTO();
        dto.setId(documentType.getId());
        dto.setInitials(documentType.getIniti());
        dto.setDocumentName(documentType.getDocumentN());
        return dto;
    }

    @Override
    public DocumentType toDocumentType(DocumentTypeDTO dto) {
        if (dto == null) {
            return null;
        }

    DocumentType entity = new DocumentType();
    entity.setId(dto.getId());
    entity.setIniti(dto.getInitials());
    entity.setDocumentN(dto.getDocumentName());
    return entity;
}
}

