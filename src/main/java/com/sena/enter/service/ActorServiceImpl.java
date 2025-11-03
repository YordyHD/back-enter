package com.sena.enter.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sena.enter.dto.ActorDTO;
import com.sena.enter.mapper.ActorMapper;
import com.sena.enter.models.Actor;
import com.sena.enter.repository.ActorRepository;

@Service
@Transactional
public class ActorServiceImpl implements ActorService {

    private final ActorRepository actorRepository;
    private final ActorMapper actorMapper;

    public ActorServiceImpl(ActorRepository actorRepository, ActorMapper actorMapper) {
        this.actorRepository = actorRepository;
        this.actorMapper = actorMapper;
    }

    @Override
    public ActorDTO save(ActorDTO actorDTO) {
        if (actorDTO == null) return null;
        Actor entity = actorMapper.toActor(actorDTO);
        Actor saved = actorRepository.save(entity);
        return actorMapper.toDTO(saved);
    }

    @Override
    public ActorDTO update(ActorDTO actorDTO) {
        if (actorDTO == null || actorDTO.getId() == null) return save(actorDTO);
        Actor entity = actorMapper.toActor(actorDTO);
        Actor saved = actorRepository.save(entity);
        return actorMapper.toDTO(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ActorDTO> findOne(Long id) {
        if (id == null) return Optional.empty();
        return actorRepository.findById(id).map(actorMapper::toDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ActorDTO> findAll() {
        return actorRepository.findAll().stream()
            .map(actorMapper::toDTO)
            .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if (id == null) return;
        actorRepository.deleteById(id);
    }
}
