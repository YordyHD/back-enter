package com.sena.enter.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import com.sena.enter.dto.DepartamentDTO;
import com.sena.enter.mapper.DepartamentMapper;
import com.sena.enter.models.Departament;
import com.sena.enter.repository.DepartamentRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class DepartamentServiceImpl implements DepartamentService {

    private final DepartamentRepository departamentRepository;
    private final DepartamentMapper departamentMapper;

    public DepartamentServiceImpl(DepartamentRepository departamentRepository, DepartamentMapper departamentMapper) {
        this.departamentRepository = departamentRepository;
        this.departamentMapper = departamentMapper;
    }

    @Override
    public DepartamentDTO save(DepartamentDTO departamentDTO) {
        if (departamentDTO == null) return null;
        Departament entity = departamentMapper.toDepartament(departamentDTO);
        Departament saved = departamentRepository.save(entity);
        return departamentMapper.toDto(saved);
    }

    @Override
    public DepartamentDTO update(DepartamentDTO departamentDTO) {
        if (departamentDTO == null || departamentDTO.getId() == null) {
            return save(departamentDTO);
        }
        Departament entity = departamentMapper.toDepartament(departamentDTO);
        Departament saved = departamentRepository.save(entity);
        return departamentMapper.toDto(saved);
    }

    @Override
    public DepartamentDTO findOne(Long id) {
        if (id == null) return null;
        return departamentRepository.findById(id)
            .map(departamentMapper::toDto)
            .orElse(null);
    }

    @Override
    public List<DepartamentDTO> findAll() {
        return departamentRepository.findAll().stream()
            .map(departamentMapper::toDto)
            .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if (id != null) {
            departamentRepository.deleteById(id);
        }
    }
}
