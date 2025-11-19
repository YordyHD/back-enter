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

import com.sena.enter.dto.DepartamentDTO;
import com.sena.enter.service.DepartamentService;

@RestController
@RequestMapping("/api/departaments")
public class DepartamentController {

    private final DepartamentService departamentService;

    public DepartamentController(DepartamentService departamentService) {
        this.departamentService = departamentService;
    }

    @PostMapping("/create")
    public ResponseEntity<DepartamentDTO> create(@RequestBody DepartamentDTO departamentDTO) {
        DepartamentDTO saved = departamentService.save(departamentDTO);
        if (saved == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartamentDTO> update(@PathVariable Long id, @RequestBody DepartamentDTO departamentDTO) {
        departamentDTO.setId(id);
        DepartamentDTO updated = departamentService.update(departamentDTO);
        if (updated == null) {
            return ResponseEntity.notFound().build(); 
        }
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartamentDTO> findOne(@PathVariable Long id) {
        DepartamentDTO departament = departamentService.findOne(id);
        if (departament == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(departament);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<DepartamentDTO>> findAll() {
        List<DepartamentDTO> departaments = departamentService.findAll();
        return ResponseEntity.ok(departaments);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        departamentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}