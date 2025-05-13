package com.ifts.aula_virtual_backend.service.impl;

import com.ifts.aula_virtual_backend.dto.SubjectDto;
import com.ifts.aula_virtual_backend.persistence.entity.ProfessorEntity;
import com.ifts.aula_virtual_backend.persistence.entity.SubjectEntity;
import com.ifts.aula_virtual_backend.persistence.repository.SubjectRepository;
import com.ifts.aula_virtual_backend.persistence.repository.UserRepository;
import com.ifts.aula_virtual_backend.service.SubjectService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SubjectServiceImpl implements SubjectService
{
    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void createSubject(SubjectEntity subject)
    {
        ProfessorEntity professor = (ProfessorEntity) userRepository.findUserEntityByDni(subject.getProfessor().getDni())
                .orElseThrow(() -> new RuntimeException("Profesor no encontrado"));

        subject.setProfessor(professor);

        subjectRepository.save(subject);
    }

    @Override
    public List<SubjectDto> getAllSubjects()
    {
        List<SubjectEntity> subjects = subjectRepository.findAll();

        List<SubjectDto> subjectDtos = new ArrayList<>();
        for (SubjectEntity subject : subjects)
        {
            SubjectDto subjectDto = new SubjectDto(
                    subject.getCode(),
                    subject.getAcademicPlan(),
                    subject.getName(),
                    subject.getYear(),
                    subject.getPeriod(),
                    subject.getProfessor().getUsername(),
                    subject.getProfessor().getLastname()
            );
            subjectDtos.add(subjectDto);
        }
        return subjectDtos;
    }

    @Override
    public Optional<SubjectEntity> getSubjectByCode(Long code)
    {
        return subjectRepository.findById(code);
    }

    @Override
    public void deleteSubjectByCode(Long code)
    {
        if (this.getSubjectByCode(code).isPresent())
        {
            subjectRepository.deleteById(code);
        } else
        {
            throw new EntityNotFoundException("Subject with Code " + code + " no exists");
        }
    }
}
