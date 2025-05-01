package com.ifts.aula_virtual_backend.service;

import com.ifts.aula_virtual_backend.dto.CoursesConsultationDto;
import com.ifts.aula_virtual_backend.dto.CoursesInscriptionDto;

import java.util.List;

public interface CoursesConsultationService
{
    void enrollSubjects(String dni, CoursesInscriptionDto inscriptionDto);

    List<CoursesConsultationDto> getAllCoursesByDni(String dni);
}
