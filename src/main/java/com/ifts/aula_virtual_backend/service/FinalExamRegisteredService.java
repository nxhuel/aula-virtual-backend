package com.ifts.aula_virtual_backend.service;

import com.ifts.aula_virtual_backend.dto.FinalExamRegisteredDto;
import com.ifts.aula_virtual_backend.dto.FinalExamRegisteredResponseDto;

import java.util.List;

public interface FinalExamRegisteredService
{
    void registerToFinalExam(FinalExamRegisteredDto finalExamRegisteredDto, String dni);

    List<FinalExamRegisteredResponseDto> getMyFinalExamRegistrations(String dni);
}
