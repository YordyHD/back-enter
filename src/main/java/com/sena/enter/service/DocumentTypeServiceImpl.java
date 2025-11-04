package com.sena.enter.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.sena.enter.dto.DocumentTypeDTO;
import com.sena.enter.mapper.DocumentTypeMapper;
import com.sena.enter.models.DocumentType;
import com.sena.enter.repository.DocumentTypeRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class DocumentTypeServiceImpl implements DocumentTypeService {

    private final DocumentTypeRepository documentTypeRepository;
    private final DocumentTypeMapper documentTypeMapper;

    public DocumentTypeServiceImpl(DocumentTypeRepository documentTypeRepository, DocumentTypeMapper documentTypeMapper) {
        this.documentTypeRepository = documentTypeRepository;
        this.documentTypeMapper = documentTypeMapper;
    }

    @Override
    public DocumentTypeDTO create(DocumentTypeDTO documentTypeDTO) {
        if (documentTypeDTO == null) return null;
        DocumentType entity = documentTypeMapper.toDocumentType(documentTypeDTO);
        DocumentType saved = documentTypeRepository.save(entity);
        return documentTypeMapper.toDto(saved);
    }

    @Override
    public DocumentTypeDTO update(DocumentTypeDTO documentTypeDTO) {
        if (documentTypeDTO == null || documentTypeDTO.getId() == null) {
            return create(documentTypeDTO);
        }
        DocumentType entity = documentTypeMapper.toDocumentType(documentTypeDTO);
        DocumentType saved = documentTypeRepository.save(entity);
        return documentTypeMapper.toDto(saved);
    }

    @Override
    public DocumentTypeDTO findOne(Long id) {
        if (id == null) return null;
        return documentTypeRepository.findById(id)
            .map(documentTypeMapper::toDto)
            .orElse(null);
    }

    @Override
    public List<DocumentTypeDTO> findAll() {
        return documentTypeRepository.findAll().stream()
            .map(documentTypeMapper::toDto)
            .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if (id != null) {
            documentTypeRepository.deleteById(id);
        }
    }
}
