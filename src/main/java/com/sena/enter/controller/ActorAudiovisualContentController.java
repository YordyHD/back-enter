package com.sena.enter.controller;

import com.sena.enter.dto.ActorAudiovisualContentDTO;
import com.sena.enter.models.ActorAudiovisualContentId;
import com.sena.enter.service.ActorAudiovisualContentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/actor-audiovisual")
public class ActorAudiovisualContentController {

    private final ActorAudiovisualContentService actorAudiovisualContentService;

    public ActorAudiovisualContentController(ActorAudiovisualContentService actorAudiovisualContentService) {
        this.actorAudiovisualContentService = actorAudiovisualContentService;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ActorAudiovisualContentDTO dto) {
        try {
            ActorAudiovisualContentDTO created = actorAudiovisualContentService.save(dto);
            if (created == null) {
                return ResponseEntity.badRequest().body(Map.of(
                        "mensaje", "Datos inválidos o incompletos"
                ));
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                    "mensaje", "Asociación actor-contenido creada exitosamente",
                    "data", created
            ));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(Map.of(
                    "error", "Error al crear la asociación",
                    "detalle", e.getMessage()
            ));
        }
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<ActorAudiovisualContentDTO> list = actorAudiovisualContentService.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{actorId}/{contentId}")
    public ResponseEntity<?> getById(@PathVariable Long actorId, @PathVariable Long contentId) {
        ActorAudiovisualContentId id = new ActorAudiovisualContentId(actorId, contentId);
        return actorAudiovisualContentService.findOne(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("mensaje", "Asociación no encontrada")));
    }

    @PutMapping("/{actorId}/{contentId}")
    public ResponseEntity<?> update(
            @PathVariable Long actorId,
            @PathVariable Long contentId,
            @RequestBody ActorAudiovisualContentDTO dto
    ) {
        dto.setId(new ActorAudiovisualContentId(actorId, contentId));
        ActorAudiovisualContentDTO updated = actorAudiovisualContentService.update(dto);
        if (updated == null) {
            return ResponseEntity.badRequest().body(Map.of(
                    "mensaje", "No se pudo actualizar la asociación"
            ));
        }
        return ResponseEntity.ok(Map.of(
                "mensaje", "Asociación actualizada correctamente",
                "data", updated
        ));
    }

    @DeleteMapping("/{actorId}/{contentId}")
    public ResponseEntity<?> delete(@PathVariable Long actorId, @PathVariable Long contentId) {
        try {
            actorAudiovisualContentService.delete(new ActorAudiovisualContentId(actorId, contentId));
            return ResponseEntity.ok(Map.of("mensaje", "Asociación eliminada correctamente"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(Map.of(
                    "error", "No se pudo eliminar la asociación",
                    "detalle", e.getMessage()
            ));
        }
    }
}