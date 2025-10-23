package com.sena.enter.mapper;

import com.sena.enter.dto.ActorDTO;
import com.sena.enter.models.Actor;

public interface ActorMapper {

    Actor toActor(ActorDTO dto);

    ActorDTO toDTO(Actor actor);
}
