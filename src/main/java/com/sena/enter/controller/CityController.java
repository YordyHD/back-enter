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

import com.sena.enter.dto.CityDTO;
import com.sena.enter.service.CityService;

@RestController
@RequestMapping("/api/cities")
public class CityController {

    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @PostMapping
    public ResponseEntity<CityDTO> create(@RequestBody CityDTO cityDTO) {
        CityDTO saved = cityService.save(cityDTO);
        if (saved == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CityDTO> update(@PathVariable Long id, @RequestBody CityDTO cityDTO) {
        cityDTO.setId(id);
        CityDTO updated = cityService.update(cityDTO);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CityDTO> findOne(@PathVariable Long id) {
        CityDTO city = cityService.findOne(id);
        if (city == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(city);
    }

    @GetMapping
    public ResponseEntity<List<CityDTO>> findAll() {
        List<CityDTO> cities = cityService.findAll();
        return ResponseEntity.ok(cities);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        cityService.delete(id);
        return ResponseEntity.noContent().build();
    }
}