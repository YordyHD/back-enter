package com.sena.enter.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.sena.enter.dto.CityDTO;
import com.sena.enter.mapper.CityMapper;
import com.sena.enter.models.City;
import com.sena.enter.repository.CityRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;
    private final CityMapper cityMapper;

    public CityServiceImpl(CityRepository cityRepository, CityMapper cityMapper) {
        this.cityRepository = cityRepository;
        this.cityMapper = cityMapper;
    }

    @Override
    public CityDTO save(CityDTO cityDTO) {
        if (cityDTO == null) return null;
        City entity = cityMapper.toCity(cityDTO);
        City saved = cityRepository.save(entity);
        return cityMapper.toDto(saved);
    }

    @Override
    public CityDTO update(CityDTO cityDTO) {
        if (cityDTO == null || cityDTO.getId() == null) {
            return save(cityDTO);
        }
        City entity = cityMapper.toCity(cityDTO);
        City saved = cityRepository.save(entity);
        return cityMapper.toDto(saved);
    }

    @Override
    public CityDTO findOne(Long id) {
        if (id == null) return null;
        return cityRepository.findById(id)
            .map(cityMapper::toDto)
            .orElse(null);
    }

    @Override
    public List<CityDTO> findAll() {
        return cityRepository.findAll().stream()
            .map(cityMapper::toDto)
            .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if (id != null) {
            cityRepository.deleteById(id);
        }
    }
}