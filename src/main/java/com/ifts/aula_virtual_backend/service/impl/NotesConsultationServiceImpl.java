package com.ifts.aula_virtual_backend.service.impl;

import com.ifts.aula_virtual_backend.dto.NotesConsultationDto;
import com.ifts.aula_virtual_backend.dto.NotesUploadDto;
import com.ifts.aula_virtual_backend.persistence.entity.StudentEntity;
import com.ifts.aula_virtual_backend.persistence.entity.SubjectEntity;
import com.ifts.aula_virtual_backend.persistence.entity.SubjectInscribedEntity;
import com.ifts.aula_virtual_backend.persistence.entity.UserEntity;
import com.ifts.aula_virtual_backend.persistence.repository.SubjectInscribedRepository;
import com.ifts.aula_virtual_backend.persistence.repository.UserRepository;
import com.ifts.aula_virtual_backend.service.NotesConsultationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotesConsultationServiceImpl implements NotesConsultationService
{

    @Autowired
    private SubjectInscribedRepository subjectInscribedRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public void uploadNotes(String dni, NotesUploadDto notesUploadDto)
    {
        UserEntity student = userRepository.findUserEntityByDni(dni)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));

        SubjectInscribedEntity inscription = subjectInscribedRepository
                .findByStudent_DniAndSubject_Name(dni, notesUploadDto.getSubjectInscribed())
                .orElseThrow(() -> new RuntimeException("No se encontró la inscripción a la materia: " + notesUploadDto.getSubjectInscribed()));

        if (!inscription.getStudent().getId().equals(student.getId())) {
            throw new RuntimeException("La inscripción no pertenece al estudiante con DNI: " + dni);
        }

        inscription.setFinalCourseGrade(notesUploadDto.getFinalCourseGrade());
        inscription.setFinalExamGrade(notesUploadDto.getFinalExamGrade());

        subjectInscribedRepository.save(inscription);
    }

    @Override
    public List<NotesConsultationDto> getAllNotesByDni(String dni)
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
                    return new NotesConsultationDto(
                            inscription.getId(),
                            subject.getYear(),
                            subject.getName(),
                            subject.getStartDateCourse(),
                            inscription.getFinalCourseGrade(),
                            subject.getFinalDateCourse(),
                            inscription.getFinalExamGrade()
                    );
                })
                .collect(Collectors.toList());
    }
}
