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

import com.sena.enter.dto.SexDTO;
import com.sena.enter.service.SexService;

@RestController
@RequestMapping("/api/sex")
public class SexController {

    private final SexService sexService;

    public SexController(SexService sexService) {
        this.sexService = sexService;
    }

    @PostMapping("/create")
    public ResponseEntity<SexDTO> create(@RequestBody SexDTO sexDTO) {
        SexDTO created = sexService.save(sexDTO);
        if (created == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(created);
    }

    @PutMapping("/update")
    public ResponseEntity<SexDTO> update(@RequestBody SexDTO sexDTO) {
        SexDTO updated = sexService.update(sexDTO);
        if (updated == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<SexDTO> findOne(@PathVariable Long id) {
        SexDTO found = sexService.findOne(id);
        return (found != null) ? ResponseEntity.ok(found) : ResponseEntity.notFound().build();
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<SexDTO>> findAll() {
        List<SexDTO> list = sexService.findAll();
        return ResponseEntity.ok(list);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        sexService.delete(id);
        return ResponseEntity.noContent().build();
    }
}