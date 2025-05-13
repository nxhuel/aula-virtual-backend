package com.ifts.aula_virtual_backend.service.impl;

import com.ifts.aula_virtual_backend.dto.FinalExamRegisteredDto;
import com.ifts.aula_virtual_backend.dto.FinalExamRegisteredResponseDto;
import com.ifts.aula_virtual_backend.persistence.entity.*;
import com.ifts.aula_virtual_backend.persistence.repository.FinalExamRegisteredRepository;
import com.ifts.aula_virtual_backend.persistence.repository.FinalExamRepository;
import com.ifts.aula_virtual_backend.persistence.repository.SubjectInscribedRepository;
import com.ifts.aula_virtual_backend.persistence.repository.UserRepository;
import com.ifts.aula_virtual_backend.service.FinalExamRegisteredService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class FinalExamRegisteredServiceImpl implements FinalExamRegisteredService
{

    @Autowired
    private SubjectInscribedRepository subjectInscribedRepository;

    @Autowired
    private FinalExamRepository finalExamRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FinalExamRegisteredRepository finalExamRegisteredRepository;

    @Override
    public void registerToFinalExam(FinalExamRegisteredDto finalExamRegisteredDto, String dni)
    {
        Integer randomNumber = new Random().nextInt(900000) + 100000; // Genera número de 6 cifras

        StudentEntity student = (StudentEntity) userRepository.findUserEntityByDni(dni)
                .orElseThrow(() -> new UsernameNotFoundException("Alumno no encontrado"));

        SubjectInscribedEntity subjectInscribed = subjectInscribedRepository
                .findByStudent_DniAndSubject_Name(dni, finalExamRegisteredDto.getSubjectInscribed())
                .orElseThrow(() -> new IllegalArgumentException("No estás inscripto en esa materia"));

        FinalExamEntity finalExam = finalExamRepository
                .findByFinalDateAndSubject_Name(finalExamRegisteredDto.getFinalExam(), finalExamRegisteredDto.getSubjectInscribed())
                .orElseThrow(() -> new IllegalArgumentException("Examen final no encontrado"));

        FinalExamRegisteredEntity registered = new FinalExamRegisteredEntity();
        registered.setRegistrationNumber(randomNumber);
        registered.setSubjectInscribed(subjectInscribed);
        registered.setFinalExam(finalExam);
        registered.setRegistrationDate(LocalDate.now());
        registered.setStudent(student);

        finalExamRegisteredRepository.save(registered);
    }

    @Override
    public List<FinalExamRegisteredResponseDto> getMyFinalExamRegistrations(String dni)
    {
        List<FinalExamRegisteredEntity> registrations = finalExamRegisteredRepository
                .findAllByStudent_Dni(dni);

        return registrations.stream()
                .map(reg -> new FinalExamRegisteredResponseDto(
                        reg.getRegistrationNumber(),
                        reg.getSubjectInscribed().getSubject().getName(),
                        reg.getFinalExam().getFinalDate(),
                        reg.getSubjectInscribed().getSubject().getProfessor().getUsername(),
                        reg.getSubjectInscribed().getSubject().getProfessor().getLastname(),
                        reg.getRegistrationDate()
                ))
                .collect(Collectors.toList());
    }

}
