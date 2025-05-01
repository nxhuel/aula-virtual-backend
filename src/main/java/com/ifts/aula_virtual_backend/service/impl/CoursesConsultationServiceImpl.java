package com.ifts.aula_virtual_backend.service.impl;

import com.ifts.aula_virtual_backend.dto.CoursesConsultationDto;
import com.ifts.aula_virtual_backend.dto.CoursesInscriptionDto;
import com.ifts.aula_virtual_backend.dto.NotesConsultationDto;
import com.ifts.aula_virtual_backend.persistence.entity.StudentEntity;
import com.ifts.aula_virtual_backend.persistence.entity.SubjectEntity;
import com.ifts.aula_virtual_backend.persistence.entity.SubjectInscribedEntity;
import com.ifts.aula_virtual_backend.persistence.entity.UserEntity;
import com.ifts.aula_virtual_backend.persistence.repository.SubjectInscribedRepository;
import com.ifts.aula_virtual_backend.persistence.repository.SubjectRepository;
import com.ifts.aula_virtual_backend.persistence.repository.UserRepository;
import com.ifts.aula_virtual_backend.service.CoursesConsultationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CoursesConsultationServiceImpl implements CoursesConsultationService
{
    @Autowired
    private SubjectInscribedRepository subjectInscribedRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void enrollSubjects(String dni, CoursesInscriptionDto inscriptionDto)
    {
        StudentEntity student = (StudentEntity) userRepository.findUserEntityByDni(dni)
                .orElseThrow(() -> new UsernameNotFoundException("Alumno no encontrado"));

        List<SubjectEntity> subjects = subjectRepository.findAllById(inscriptionDto.getSubjectCode());

        List<SubjectInscribedEntity> inscriptions = new ArrayList<>();

        for (SubjectEntity subject : subjects)
        {
            boolean alreadyInscribed = subjectInscribedRepository.existsByStudentAndSubject(student, subject);

            if (!alreadyInscribed)
            {
                SubjectInscribedEntity inscription = new SubjectInscribedEntity();
                inscription.setStudent(student);
                inscription.setSubject(subject);
                inscriptions.add(inscription);
            }
        }

        subjectInscribedRepository.saveAll(inscriptions);

    }

    @Override
    public List<CoursesConsultationDto> getAllCoursesByDni(String dni)
    {
        List<SubjectInscribedEntity> inscriptions = subjectInscribedRepository.findByStudentDni(dni);

        if (inscriptions == null || inscriptions.isEmpty())
        {
            return Collections.emptyList();
        }

        return inscriptions.stream()
                .map(inscription ->
                {
                    SubjectEntity subject = inscription.getSubject();
                    return new CoursesConsultationDto(
                            inscription.getId(),
                            inscription.getStudent().getLegajo(),
                            subject.getPeriod(),
                            subject.getName(),
                            subject.getProfessor().getUsername(),
                            subject.getProfessor().getLastname()
                    );
                })
                .collect(Collectors.toList());
    }
}
