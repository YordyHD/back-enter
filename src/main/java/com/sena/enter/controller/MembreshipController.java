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

import com.sena.enter.dto.MembreshipDTO;
import com.sena.enter.service.MembreshipService;

@RestController
@RequestMapping("/api/membreships")
public class MembreshipController {

    private final MembreshipService membreshipService;

    public MembreshipController(MembreshipService membreshipService) {
        this.membreshipService = membreshipService;
    }

    @PostMapping("/create")
    public ResponseEntity<MembreshipDTO> create(@RequestBody MembreshipDTO membreshipDTO) {
        MembreshipDTO created = membreshipService.save(membreshipDTO);
        if (created == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(created);
    }

    @PutMapping("/update")
    public ResponseEntity<MembreshipDTO> update(@RequestBody MembreshipDTO membreshipDTO) {
        MembreshipDTO updated = membreshipService.update(membreshipDTO);
        if (updated == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<MembreshipDTO> findOne(@PathVariable Long id) {
        MembreshipDTO found = membreshipService.findOne(id);
        return (found != null) ? ResponseEntity.ok(found) : ResponseEntity.notFound().build();
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<MembreshipDTO>> findAll() {
        List<MembreshipDTO> list = membreshipService.findAll();
        return ResponseEntity.ok(list);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        membreshipService.delete(id);
        return ResponseEntity.noContent().build();
    }
}