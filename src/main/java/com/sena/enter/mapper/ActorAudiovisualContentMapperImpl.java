package com.sena.enter.mapper;

import org.springframework.stereotype.Component;

import com.sena.enter.dto.ActorAudiovisualContentDTO;
import com.sena.enter.dto.ActorDTO;
import com.sena.enter.dto.AudiovisualContentDTO;
import com.sena.enter.models.Actor;
import com.sena.enter.models.ActorAudiovisualContent;
import com.sena.enter.models.ActorAudiovisualContentId;
import com.sena.enter.models.AudiovisualContent;

@Component
public class ActorAudiovisualContentMapperImpl implements ActorAudiovvisualContentMapper{
    @Override
    public ActorAudiovisualContentDTO toDTO(ActorAudiovisualContent actorAudiovisualContent){
        if (actorAudiovisualContent == null){
            return null;
        }

        ActorAudiovisualContentDTO dto = new ActorAudiovisualContentDTO();
        if (actorAudiovisualContent.getId() != null){
            dto.setId(actorAudiovisualContent.getId().getActorId());
        }
        dto.setActorType(actorAudiovisualContent.getActorType());
        dto.setCharacter(actorAudiovisualContent.getCharac());

        if (actorAudiovisualContent.getActor() != null) {
            ActorDTO a = new ActorDTO();
            a.setId(actorAudiovisualContent.getActor().getId());
            dto.setActor(a);
        }
        if(actorAudiovisualContent.getAudiovisualContent() != null){
            AudiovisualContentDTO ac = new AudiovisualContentDTO();
            ac.setId(actorAudiovisualContent.getAudiovisualContent().getId());
            dto.setAudiovisualContent(ac);
        }

        return dto;
    }

    @Override
    public ActorAudiovisualContent toActorAudiovisualContent(ActorAudiovisualContentDTO dto){
        if (dto == null){
            return null;
        }
        ActorAudiovisualContent actorAudiovisualContent = new ActorAudiovisualContent();


        Long actorId = null;
        Long audiovisualContentId = null;
        if (dto.getActor() != null) actorId = dto.getActor().getId();
        if (dto.getAudiovisualContent() != null) audiovisualContentId = dto.getAudiovisualContent().getId();
        if(actorId != null || audiovisualContentId != null) {
            ActorAudiovisualContentId id = new ActorAudiovisualContentId(actorId, audiovisualContentId);
            actorAudiovisualContent.setId(id);
        }

        actorAudiovisualContent.setActorType(dto.getActorType());
        actorAudiovisualContent.setCharac(dto.getCharacter());

        if (actorId != null){
            Actor a = new Actor();
            a.setId(actorId);
            actorAudiovisualContent.setActor(a);
        }
        if (audiovisualContentId !=null){
            AudiovisualContent ac = new AudiovisualContent();
            ac.setId(audiovisualContentId);
            actorAudiovisualContent.setAudiovisualContent(ac);
        }

        return actorAudiovisualContent;
    }
}
