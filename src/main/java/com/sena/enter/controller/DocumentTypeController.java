package com.sena.enter.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sena.enter.dto.DocumentTypeDTO;
import com.sena.enter.service.DocumentTypeService;

@RestController 
@RequestMapping("/api/document-types")
public class DocumentTypeController {

    private final DocumentTypeService documentTypeService;

    public DocumentTypeController(DocumentTypeService documentTypeService) {
        this.documentTypeService = documentTypeService;
    }

    @PostMapping("/create")
    public ResponseEntity<DocumentTypeDTO> create(@RequestBody DocumentTypeDTO documentTypeDTO) {
        DocumentTypeDTO created = documentTypeService.create(documentTypeDTO);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/update")
    public ResponseEntity<DocumentTypeDTO> update(@RequestBody DocumentTypeDTO documentTypeDTO) {
        DocumentTypeDTO updated = documentTypeService.update(documentTypeDTO);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<DocumentTypeDTO> findOne(@PathVariable Long id) {
        DocumentTypeDTO found = documentTypeService.findOne(id);
        return (found != null) ? ResponseEntity.ok(found) : ResponseEntity.notFound().build();
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<DocumentTypeDTO>> findAll() {
        List<DocumentTypeDTO> list = documentTypeService.findAll();
        return ResponseEntity.ok(list);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        documentTypeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}