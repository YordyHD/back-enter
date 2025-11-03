package com.sena.enter.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.sena.enter.dto.DirectorDTO;
import com.sena.enter.mapper.DirectorMapper;
import com.sena.enter.models.Director;
import com.sena.enter.repository.DirectorRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class DirectorServiceImpl implements DirectorService {

    private final DirectorRepository directorRepository;
    private final DirectorMapper directorMapper;

    public DirectorServiceImpl(DirectorRepository directorRepository, DirectorMapper directorMapper) {
        this.directorRepository = directorRepository;
        this.directorMapper = directorMapper;
    }

    @Override
    public DirectorDTO save(DirectorDTO directorDTO) {
        if (directorDTO == null) return null;
        Director entity = directorMapper.toDirector(directorDTO);
        Director saved = directorRepository.save(entity);
        return directorMapper.toDTO(saved);
    }

    @Override
    public DirectorDTO update(DirectorDTO directorDTO) {
        if (directorDTO == null || directorDTO.getId() == null) {
            return save(directorDTO);
        }
        Director entity = directorMapper.toDirector(directorDTO);
        Director saved = directorRepository.save(entity);
        return directorMapper.toDTO(saved);
    }

    @Override
    public DirectorDTO findOne(Long id) {
        if (id == null) return null;
        return directorRepository.findById(id)
            .map(directorMapper::toDTO)
            .orElse(null);
    }

    @Override
    public List<DirectorDTO> findAll() {
        return directorRepository.findAll().stream()
            .map(directorMapper::toDTO)
            .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if (id != null) {
            directorRepository.deleteById(id);
        }
    }
}