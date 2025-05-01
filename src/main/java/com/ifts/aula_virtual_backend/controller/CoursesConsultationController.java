package com.ifts.aula_virtual_backend.controller;

import com.ifts.aula_virtual_backend.dto.CoursesConsultationDto;
import com.ifts.aula_virtual_backend.dto.CoursesInscriptionDto;
import com.ifts.aula_virtual_backend.service.CoursesConsultationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/courses")
@PreAuthorize("denyAll()")
public class CoursesConsultationController
{
    @Autowired
    private CoursesConsultationService coursesConsultationService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'STUDENT')")
    public ResponseEntity<String> enrollSubjects(@RequestBody CoursesInscriptionDto coursesInscriptionDto, Authentication authentication)
    {
        String dni = authentication.getName();
        coursesConsultationService.enrollSubjects(dni, coursesInscriptionDto);
        return new ResponseEntity<>("Inscripción realizada exitosamente" ,HttpStatus.OK);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'STUDENT')")
    public ResponseEntity<List<CoursesConsultationDto>> getCoursesByStudent(Authentication authentication)
    {
        String dni = authentication.getName();
        List<CoursesConsultationDto> courses = coursesConsultationService.getAllCoursesByDni(dni);
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }
}
