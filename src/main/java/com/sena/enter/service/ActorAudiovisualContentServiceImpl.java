package com.sena.enter.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.sena.enter.dto.ActorAudiovisualContentDTO;
import com.sena.enter.mapper.ActorAudiovisualContentMapper;
import com.sena.enter.models.ActorAudiovisualContent;
import com.sena.enter.models.ActorAudiovisualContentId;
import com.sena.enter.repository.ActorAudiovisualContentRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ActorAudiovisualContentServiceImpl implements ActorAudiovisualContentService {

    private final ActorAudiovisualContentRepository actorAudiovisualContentRepository;
    private final ActorAudiovisualContentMapper actorAudiovisualContentMapper;

    public ActorAudiovisualContentServiceImpl(ActorAudiovisualContentRepository actorAudiovisualContentRepository,
            ActorAudiovisualContentMapper actorAudiovisualContentMapper) {
        this.actorAudiovisualContentRepository = actorAudiovisualContentRepository;
        this.actorAudiovisualContentMapper = actorAudiovisualContentMapper;
    }

    @Override
    public ActorAudiovisualContentDTO save(ActorAudiovisualContentDTO actorAudiovisualContentDTO) {
        if (actorAudiovisualContentDTO == null) return null;
        ActorAudiovisualContent entity = actorAudiovisualContentMapper.toActorAudiovisualContent(actorAudiovisualContentDTO);
        ActorAudiovisualContent saved = actorAudiovisualContentRepository.save(entity);
        return actorAudiovisualContentMapper.toDTO(saved);
    }

    @Override
    public ActorAudiovisualContentDTO update(ActorAudiovisualContentDTO actorAudiovisualContentDTO) {
        if (actorAudiovisualContentDTO == null || actorAudiovisualContentDTO.getId() == null) {
            return save(actorAudiovisualContentDTO);
        }
        ActorAudiovisualContent entity = actorAudiovisualContentMapper.toActorAudiovisualContent(actorAudiovisualContentDTO);
        ActorAudiovisualContent saved = actorAudiovisualContentRepository.save(entity);
        return actorAudiovisualContentMapper.toDTO(saved);
    }

    @Override
    public Optional<ActorAudiovisualContentDTO> findOne(ActorAudiovisualContentId id) {
        if (id == null) return Optional.empty();
        return actorAudiovisualContentRepository.findById(id)
            .map(actorAudiovisualContentMapper::toDTO);
    }

    @Override
    public List<ActorAudiovisualContentDTO> findAll() {
        return actorAudiovisualContentRepository.findAll().stream()
            .map(actorAudiovisualContentMapper::toDTO)
            .collect(Collectors.toList());
    }

    @Override
    public void delete(ActorAudiovisualContentId id) {
        if (id == null) return;
        actorAudiovisualContentRepository.deleteById(id);
    }
}
