package com.sena.enter.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.sena.enter.dto.SexDTO;
import com.sena.enter.mapper.SexMapper;
import com.sena.enter.models.Sex;
import com.sena.enter.repository.SexRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class SexServiceImpl implements SexService {

    private final SexRepository sexRepository;
    private final SexMapper sexMapper;

    public SexServiceImpl(SexRepository sexRepository, SexMapper sexMapper) {
        this.sexRepository = sexRepository;
        this.sexMapper = sexMapper;
    }

    @Override
    public SexDTO save(SexDTO sexDTO) {
        if (sexDTO == null) {
            return null;
        }
        Sex sex = sexMapper.toSex(sexDTO);
        if (sex == null) {
            return null;
        }
        sex = sexRepository.save(sex);
        return sexMapper.toDTO(sex);
    }

    @Override
    public SexDTO update(SexDTO sexDTO) {
        if (sexDTO == null || sexDTO.getId() == null) {
            return null;
        }
        Sex sex = sexMapper.toSex(sexDTO);
        sex = sexRepository.save(sex);
        return sexMapper.toDTO(sex);
    }

    @Override
    public SexDTO findOne(Long id) {
        if (id == null) {
            return null;
        }
        return sexRepository.findById(id)
            .map(sexMapper::toDTO)
            .orElse(null);
    }

    @Override
    public List<SexDTO> findAll() {
        return sexRepository.findAll().stream()
            .map(sexMapper::toDTO)
            .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if (id != null && sexRepository.existsById(id)) {
            sexRepository.deleteById(id);
        }
    }
}
