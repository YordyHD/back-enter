package com.sena.enter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sena.enter.models.ActorAudiovisualContent;
import com.sena.enter.models.ActorAudiovisualContentId;
import com.sena.enter.models.Actor;
import com.sena.enter.models.AudiovisualContent;

@Repository
public interface ActorAudiovisualContentRepository extends JpaRepository<ActorAudiovisualContent, ActorAudiovisualContentId> {

    List<ActorAudiovisualContent> findByActor_Id(Long actorId);

    List<ActorAudiovisualContent> findByAudiovisualContent_Id(Long audiovisualContentId);

    List<ActorAudiovisualContent> findByActor(Actor actor);
    List<ActorAudiovisualContent> findByAudiovisualContent(AudiovisualContent audiovisualContent);
}