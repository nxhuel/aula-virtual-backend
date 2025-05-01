package com.ifts.aula_virtual_backend.service.impl;

import com.ifts.aula_virtual_backend.persistence.entity.SubjectInscribedEntity;
import com.ifts.aula_virtual_backend.persistence.repository.SubjectInscribedRepository;
import com.ifts.aula_virtual_backend.service.SubjectInscribedService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectInscribedServiceImpl implements SubjectInscribedService
{
    @Autowired
    private SubjectInscribedRepository subjectInscribedRepository;

    @Override
    public void registerSubjectInscribed(SubjectInscribedEntity subjectInscribed)
    {
        subjectInscribedRepository.save(subjectInscribed);
    }

    @Override
    public List<SubjectInscribedEntity> getAllSubjectsInscribed()
    {
        return subjectInscribedRepository.findAll();
    }

    @Override
    public Optional<SubjectInscribedEntity> getSubjectInscribedById(Long id)
    {
        return subjectInscribedRepository.findById(id);
    }

    @Override
    public void deleteSubjectInscribedById(Long id)
    {
        if (this.subjectInscribedRepository.findById(id).isPresent())
        {
            subjectInscribedRepository.deleteById(id);
        } else
        {
            throw new EntityNotFoundException("Subject Inscribed with ID " + id + " no exists");
        }
    }
}
