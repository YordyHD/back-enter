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

import com.sena.enter.dto.DirectorDTO;
import com.sena.enter.service.DirectorService;

@RestController
@RequestMapping("/api/directors")
public class DirectorController {

    private final DirectorService directorService;

    public DirectorController(DirectorService directorService) {
        this.directorService = directorService;
    }

    @PostMapping
    public ResponseEntity<DirectorDTO> create(@RequestBody DirectorDTO directorDTO) {
        DirectorDTO saved = directorService.save(directorDTO);
        if (saved == null) {
            return ResponseEntity.badRequest().build(); 
        }
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DirectorDTO> update(@PathVariable Long id, @RequestBody DirectorDTO directorDTO) {
        directorDTO.setId(id);
        DirectorDTO updated = directorService.update(directorDTO);
        if (updated == null) {
            return ResponseEntity.notFound().build(); 
        }
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DirectorDTO> findOne(@PathVariable Long id) {
        DirectorDTO director = directorService.findOne(id);
        if (director == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(director);
    }

    @GetMapping
    public ResponseEntity<List<DirectorDTO>> findAll() {
        List<DirectorDTO> directors = directorService.findAll();
        return ResponseEntity.ok(directors);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        directorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}