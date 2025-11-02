package com.sena.enter.mapper;

import com.sena.enter.dto.ActorAudiovisualContentDTO;
import com.sena.enter.models.ActorAudiovisualContent;

public interface ActorAudiovisualContentMapper {

    ActorAudiovisualContent toActorAudiovisualContent(ActorAudiovisualContentDTO dto);
    ActorAudiovisualContentDTO toDTO(ActorAudiovisualContent actorAudiovisualContent);
}
