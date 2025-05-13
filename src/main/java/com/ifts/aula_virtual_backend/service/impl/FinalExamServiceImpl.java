package com.ifts.aula_virtual_backend.service.impl;

import com.ifts.aula_virtual_backend.dto.FinalExamDto;
import com.ifts.aula_virtual_backend.persistence.entity.AcademicPlanEnum;
import com.ifts.aula_virtual_backend.persistence.entity.FinalExamEntity;
import com.ifts.aula_virtual_backend.persistence.entity.SubjectEntity;
import com.ifts.aula_virtual_backend.persistence.repository.FinalExamRepository;
import com.ifts.aula_virtual_backend.persistence.repository.SubjectRepository;
import com.ifts.aula_virtual_backend.service.FinalExamService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FinalExamServiceImpl implements FinalExamService
{
    @Autowired
    private FinalExamRepository finalExamRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public void createFinalExam(FinalExamDto finalExamDto)
    {
        SubjectEntity subject = subjectRepository.findByName(finalExamDto.getSubject())
                .orElseThrow(() -> new EntityNotFoundException("Materia no encontrada: " + finalExamDto.getSubject()));

        FinalExamEntity finalExam = new FinalExamEntity();
        finalExam.setAcademicPlan(AcademicPlanEnum.valueOf(finalExamDto.getAcademicPlanEnum()));
        finalExam.setSubject(subject);
        finalExam.setFinalDate(finalExamDto.getFinalDate());

        finalExamRepository.save(finalExam);
    }

    @Override
    public List<FinalExamDto> getAllFinalExam()
    {
        List<FinalExamEntity> exams = finalExamRepository.findAll();
        List<FinalExamDto> examDtos = new ArrayList<>();

        for (FinalExamEntity exam : exams) {
            FinalExamDto dto = new FinalExamDto();
            dto.setAcademicPlanEnum(exam.getAcademicPlan().name());
            dto.setSubject(exam.getSubject().getName());
            dto.setFinalDate(exam.getFinalDate());
            examDtos.add(dto);
        }

        return examDtos;
    }
}
