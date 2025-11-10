package com.sena.enter.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.sena.enter.dto.AudiovisualContentDTO;
import com.sena.enter.mapper.AudiovisualContentMapper;
import com.sena.enter.models.AudiovisualContent;
import com.sena.enter.repository.AudiovisualContentRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AudiovisualContentServiceImpl implements AudiovisualContentService {

    private final AudiovisualContentRepository audiovisualContentRepository;
    private final AudiovisualContentMapper audiovisualContentMapper;

    public AudiovisualContentServiceImpl(AudiovisualContentRepository audiovisualContentRepository,
            AudiovisualContentMapper audiovisualContentMapper) {
        this.audiovisualContentRepository = audiovisualContentRepository;
        this.audiovisualContentMapper = audiovisualContentMapper;
    }

    @Override
    public AudiovisualContentDTO save(AudiovisualContentDTO audiovisualContentDTO) {
        if (audiovisualContentDTO == null) return null;
        AudiovisualContent entity = audiovisualContentMapper.toAudiovisualContent(audiovisualContentDTO);
        AudiovisualContent saved = audiovisualContentRepository.save(entity);
        return audiovisualContentMapper.toDTO(saved);
    }

    @Override
    public AudiovisualContentDTO update(AudiovisualContentDTO audiovisualContentDTO) {
        if (audiovisualContentDTO == null || audiovisualContentDTO.getId() == null) {
            return save(audiovisualContentDTO);
        }
        AudiovisualContent entity = audiovisualContentMapper.toAudiovisualContent(audiovisualContentDTO);
        AudiovisualContent saved = audiovisualContentRepository.save(entity);
        return audiovisualContentMapper.toDTO(saved);
    }

    @Override
    public Optional<AudiovisualContentDTO> findOne(Long id) {
        if (id == null) return Optional.empty();
        return audiovisualContentRepository.findById(id)
            .map(audiovisualContentMapper::toDTO);
    }

    @Override
    public List<AudiovisualContentDTO> findAll() {
        return audiovisualContentRepository.findAll().stream()
            .map(audiovisualContentMapper::toDTO)
            .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if (id != null) {
            audiovisualContentRepository.deleteById(id);
        }
    }
}
