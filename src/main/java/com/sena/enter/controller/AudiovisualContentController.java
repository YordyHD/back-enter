package com.sena.enter.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sena.enter.dto.AudiovisualContentDTO;
import com.sena.enter.service.AudiovisualContentService;

@RestController
@RequestMapping("/api/audiovisual")
public class AudiovisualContentController {

    private final AudiovisualContentService audiovisualContentService;

    public AudiovisualContentController(AudiovisualContentService audiovisualContentService) {
        this.audiovisualContentService = audiovisualContentService;
    }

    @PostMapping("/create")
    public ResponseEntity<AudiovisualContentDTO> createContent(@RequestBody AudiovisualContentDTO dto) {
        AudiovisualContentDTO saved = audiovisualContentService.save(dto);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AudiovisualContentDTO> updateContent(@PathVariable Long id, @RequestBody AudiovisualContentDTO dto) {
        dto.setId(id);
        AudiovisualContentDTO updated = audiovisualContentService.update(dto);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AudiovisualContentDTO> getContent(@PathVariable Long id) {
        Optional<AudiovisualContentDTO> contentOpt = audiovisualContentService.findOne(id);
        return contentOpt.map(ResponseEntity::ok)
                        .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<AudiovisualContentDTO>> getAllContents() {
        List<AudiovisualContentDTO> contents = audiovisualContentService.findAll();
        return ResponseEntity.ok(contents);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContent(@PathVariable Long id) {
        audiovisualContentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}