package com.sena.enter.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.sena.enter.dto.MembreshipDTO;
import com.sena.enter.mapper.MembreshipMapper;
import com.sena.enter.models.Membreship;
import com.sena.enter.repository.MembreshipRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class MembreshipServiceImpl implements MembreshipService {

    private final MembreshipRepository membreshipRepository;
    private final MembreshipMapper membreshipMapper;

    public MembreshipServiceImpl(MembreshipRepository membreshipRepository, MembreshipMapper membreshipMapper) {
        this.membreshipRepository = membreshipRepository;
        this.membreshipMapper = membreshipMapper;
    }

    @Override
    public MembreshipDTO save(MembreshipDTO membreshipDTO) {
        if (membreshipDTO == null) {
            return null;
        }
        if (membreshipDTO.getMembershipName() == null) {
            return null;
        }
        Membreship membreship = membreshipMapper.toMembreship(membreshipDTO);
        if (membreship == null) {
            return null;
        }
        membreship = membreshipRepository.save(membreship);
        return membreshipMapper.toDTO(membreship);
    }

    @Override
    public MembreshipDTO update(MembreshipDTO membreshipDTO) {
        if (membreshipDTO == null || membreshipDTO.getId() == null) {
            return null;
        }
        Membreship membreship = membreshipMapper.toMembreship(membreshipDTO);
        membreship = membreshipRepository.save(membreship);
        return membreshipMapper.toDTO(membreship);
    }

    @Override
    public MembreshipDTO findOne(Long id) {
        if (id == null) {
            return null;
        }
        return membreshipRepository.findById(id)
            .map(membreshipMapper::toDTO)
            .orElse(null);
    }

    @Override
    public List<MembreshipDTO> findAll() {
        return membreshipRepository.findAll().stream()
            .map(membreshipMapper::toDTO)
            .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if (id != null && membreshipRepository.existsById(id)) {
            membreshipRepository.deleteById(id);
        }
    }
}
