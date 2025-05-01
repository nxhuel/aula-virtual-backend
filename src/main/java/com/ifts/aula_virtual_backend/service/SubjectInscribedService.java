package com.ifts.aula_virtual_backend.service;

import com.ifts.aula_virtual_backend.persistence.entity.SubjectInscribedEntity;

import java.util.List;
import java.util.Optional;

public interface SubjectInscribedService
{
    void registerSubjectInscribed(SubjectInscribedEntity subjectInscribed);

    List<SubjectInscribedEntity> getAllSubjectsInscribed();

    Optional<SubjectInscribedEntity> getSubjectInscribedById(Long id);

    void deleteSubjectInscribedById(Long id);
}
