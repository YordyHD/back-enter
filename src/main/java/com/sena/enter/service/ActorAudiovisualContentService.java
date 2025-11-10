package com.sena.enter.service;

import java.util.List;
import java.util.Optional;

import com.sena.enter.dto.ActorAudiovisualContentDTO;
import com.sena.enter.models.ActorAudiovisualContentId;

public interface ActorAudiovisualContentService {

    ActorAudiovisualContentDTO save(ActorAudiovisualContentDTO actorAudiovisualContentDTO);

    ActorAudiovisualContentDTO update(ActorAudiovisualContentDTO actorAudiovisualContentDTO);

    Optional<ActorAudiovisualContentDTO> findOne(ActorAudiovisualContentId id);

    List<ActorAudiovisualContentDTO> findAll();

    void delete(ActorAudiovisualContentId id);
}
