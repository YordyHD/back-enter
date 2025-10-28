package com.sena.enter.mapper;

import com.sena.enter.dto.AudiovisualContentDTO;
import com.sena.enter.models.AudiovisualContent;

public interface AudiovisualContentMapper {

  AudiovisualContent toAudiovisualContent(AudiovisualContentDTO dto);

  AudiovisualContentDTO toDTO(AudiovisualContent audiovisualContent);

}
