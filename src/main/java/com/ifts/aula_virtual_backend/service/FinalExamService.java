package com.ifts.aula_virtual_backend.service;

import com.ifts.aula_virtual_backend.dto.FinalExamDto;

import java.util.List;

public interface FinalExamService
{
    void createFinalExam(FinalExamDto finalExamDto);

    List<FinalExamDto> getAllFinalExam();
}
