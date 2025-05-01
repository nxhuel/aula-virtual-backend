package com.ifts.aula_virtual_backend.service;

import com.ifts.aula_virtual_backend.dto.SubjectDto;
import com.ifts.aula_virtual_backend.persistence.entity.SubjectEntity;

import java.util.List;
import java.util.Optional;

public interface SubjectService
{
    void createSubject(SubjectEntity subject);

    List<SubjectDto> getAllSubjects();

    Optional<SubjectEntity> getSubjectByCode(Long code);

    void deleteSubjectByCode(Long code);
}
