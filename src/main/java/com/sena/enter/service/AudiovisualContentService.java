package com.sena.enter.service;

import java.util.List;
import java.util.Optional;

import com.sena.enter.dto.AudiovisualContentDTO;

public interface AudiovisualContentService {


    AudiovisualContentDTO save(AudiovisualContentDTO audiovisualContentDTO);

    AudiovisualContentDTO update(AudiovisualContentDTO audiovisualContentDTO);

    Optional<AudiovisualContentDTO> findOne(Long id);

    List<AudiovisualContentDTO> findAll();

    void delete(Long id);
}
