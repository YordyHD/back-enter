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

import com.sena.enter.dto.BillMembreshipDTO;
import com.sena.enter.service.BillMembreshipService;

@RestController
@RequestMapping("/api/bill-membreships")
public class BillMembreshipController {

    private final BillMembreshipService billMembreshipService;

    public BillMembreshipController(BillMembreshipService billMembreshipService) {
        this.billMembreshipService = billMembreshipService;
    }

    @PostMapping("/create")
    public ResponseEntity<BillMembreshipDTO> create(@RequestBody BillMembreshipDTO dto) {
        BillMembreshipDTO saved = billMembreshipService.save(dto);
        if (saved == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{billId}/{membreshipId}")
    public ResponseEntity<BillMembreshipDTO> update(
            @PathVariable Long billId,
            @PathVariable Long membreshipId,
            @RequestBody BillMembreshipDTO dto) {
        if (dto.getBill() == null || dto.getMembreship() == null) {
            return ResponseEntity.badRequest().build();
        }
        dto.getBill().setId(billId);
        dto.getMembreship().setId(membreshipId);

        BillMembreshipDTO updated = billMembreshipService.update(dto);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/{billId}/{membreshipId}")
    public ResponseEntity<BillMembreshipDTO> getOne(
            @PathVariable Long billId,
            @PathVariable Long membreshipId) {
        BillMembreshipDTO dto = billMembreshipService.findOne(billId, membreshipId);
        if (dto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<BillMembreshipDTO>> getAll() {
        List<BillMembreshipDTO> list = billMembreshipService.findAll();
        return ResponseEntity.ok(list);
    }

    @DeleteMapping("/{billId}/{membreshipId}")
    public ResponseEntity<Void> delete(
            @PathVariable Long billId,
            @PathVariable Long membreshipId) {
        billMembreshipService.delete(billId, membreshipId);
        return ResponseEntity.noContent().build();
    }
}