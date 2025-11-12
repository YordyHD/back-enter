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

import com.sena.enter.dto.FilmGenreDTO;
import com.sena.enter.service.FilmGenreService;

@RestController
@RequestMapping("/api/film-genres")
public class FilmGenreController {

    private final FilmGenreService filmGenreService;

    public FilmGenreController(FilmGenreService filmGenreService) {
        this.filmGenreService = filmGenreService;
    }

    @PostMapping("/create")
    public ResponseEntity<FilmGenreDTO> create(@RequestBody FilmGenreDTO filmGenreDTO) {
        FilmGenreDTO created = filmGenreService.save(filmGenreDTO);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/update")
    public ResponseEntity<FilmGenreDTO> update(@RequestBody FilmGenreDTO filmGenreDTO) {
        FilmGenreDTO updated = filmGenreService.update(filmGenreDTO);
        if (updated == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<FilmGenreDTO> findOne(@PathVariable Long id) {
        FilmGenreDTO found = filmGenreService.findOne(id);
        return (found != null) ? ResponseEntity.ok(found) : ResponseEntity.notFound().build();
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<FilmGenreDTO>> findAll() {
        List<FilmGenreDTO> list = filmGenreService.findAll();
        return ResponseEntity.ok(list);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        filmGenreService.delete(id);
        return ResponseEntity.noContent().build();
    }
}