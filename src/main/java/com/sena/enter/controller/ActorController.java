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

import com.sena.enter.dto.ActorDTO;
import com.sena.enter.service.ActorService;


@RestController
@RequestMapping("/api/actors")
public class ActorController {

    private ActorService actorService;

    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @PostMapping
    public ResponseEntity<ActorDTO> createActor(@RequestBody ActorDTO actorDTO) {
        ActorDTO saved = actorService.save(actorDTO);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ActorDTO> updateActor(@PathVariable Long id, @RequestBody ActorDTO actorDTO) {
        actorDTO.setId(id); 
        ActorDTO updated = actorService.update(actorDTO);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ActorDTO> getActor(@PathVariable Long id) {
        Optional<ActorDTO> actorOpt = actorService.findOne(id);
        return actorOpt.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<ActorDTO>> getAllActors() {
        List<ActorDTO> actores = actorService.findAll();
        return ResponseEntity.ok(actores);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteActor(@PathVariable Long id) {
        actorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
