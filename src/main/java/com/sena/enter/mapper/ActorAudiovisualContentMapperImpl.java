package com.sena.enter.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sena.enter.dto.ActorAudiovisualContentDTO;
import com.sena.enter.models.Actor;
import com.sena.enter.models.ActorAudiovisualContent;
import com.sena.enter.models.ActorAudiovisualContentId;
import com.sena.enter.models.AudiovisualContent;

@Component
public class ActorAudiovisualContentMapperImpl implements ActorAudiovisualContentMapper{

    @Autowired
    private ActorMapper actorMapper;

    @Autowired
    private AudiovisualContentMapper audiovisualContentMapper;

    @Override
    public ActorAudiovisualContentDTO toDTO(ActorAudiovisualContent ent){
        if (ent == null) return null;

        ActorAudiovisualContentDTO dto = new ActorAudiovisualContentDTO();

        if (ent.getId() != null) {
            dto.setActorId(ent.getId().getActorId());
            dto.setAudiovisualContentId(ent.getId().getAudiovisualContentId());
            dto.setId(ent.getId().getActorId());
        }

        dto.setActorType(ent.getActorType());
        dto.setCharacter(ent.getCharac());

        if (ent.getActor() != null) {
            dto.setActor(actorMapper.toDTO(ent.getActor()));
        }

        if (ent.getAudiovisualContent() != null) {
            dto.setAudiovisualContent(audiovisualContentMapper.toDTO(ent.getAudiovisualContent()));
        }

        return dto;
    }

    @Override
    public ActorAudiovisualContent toActorAudiovisualContent(ActorAudiovisualContentDTO dto){
        if (dto == null) return null;

        ActorAudiovisualContent ent = new ActorAudiovisualContent();

        Long actorId = null;
        Long audiovisualContentId = null;

        if (dto.getActorId() != null) actorId = dto.getActorId();
        else if (dto.getActor() != null) actorId = dto.getActor().getId();

        if (dto.getAudiovisualContentId() != null) audiovisualContentId = dto.getAudiovisualContentId();
        else if (dto.getAudiovisualContent() != null) audiovisualContentId = dto.getAudiovisualContent().getId();

        if (actorId != null || audiovisualContentId != null) {
            ActorAudiovisualContentId id = new ActorAudiovisualContentId(actorId, audiovisualContentId);
            ent.setId(id);
        }

        ent.setActorType(dto.getActorType());
        ent.setCharac(dto.getCharacter());

        if (actorId != null){
            Actor a = new Actor();
            a.setId(actorId);
            ent.setActor(a);
        }

        if (audiovisualContentId != null){
            AudiovisualContent ac = new AudiovisualContent();
            ac.setId(audiovisualContentId);
            ent.setAudiovisualContent(ac);
        }

        return ent;
    }
}
