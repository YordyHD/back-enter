package com.sena.enter.mapper;

import org.springframework.stereotype.Component;
import com.sena.enter.dto.ActorDTO;
import com.sena.enter.models.Actor;


@Component
public class ActorMapperImpl implements ActorMapper {

    @Override
    public ActorDTO toDTO(Actor actor) {
        if (actor == null) {
            return null;
        }

        ActorDTO dto = new ActorDTO();
        dto.setId(actor.getId());
        dto.setNameActor(actor.getNactor());
        dto.setLastNameActor(actor.getLnactor());
        dto.setPicture(actor.getPic());
        dto.setPictureContentType(actor.getPiccontenttype());
        return dto;
    }

    @Override
    public Actor toActor(ActorDTO dto) {
        if (dto == null) {
            return null;
        }

        Actor actor = new Actor();
        actor.setId(dto.getId());
        actor.setNactor(dto.getNameActor());
        actor.setLnactor(dto.getLastNameActor());
        actor.setPic(dto.getPicture());
        actor.setPiccontenttype(dto.getPictureContentType());
        return actor;
    }
}

