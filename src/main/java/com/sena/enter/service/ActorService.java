package com.sena.enter.service;

import java.util.List;
import java.util.Optional;

import com.sena.enter.dto.ActorDTO;

public interface ActorService {

	ActorDTO save(ActorDTO actorDTO);

	ActorDTO update(ActorDTO actorDTO);

	Optional<ActorDTO> findOne(Long id);

	List<ActorDTO> findAll();

	void delete(Long id);

}
