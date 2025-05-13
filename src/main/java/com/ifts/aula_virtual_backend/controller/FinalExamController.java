package com.ifts.aula_virtual_backend.controller;

import com.ifts.aula_virtual_backend.dto.FinalExamDto;
import com.ifts.aula_virtual_backend.service.FinalExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/final-exams")
@PreAuthorize("denyAll()")
public class FinalExamController
{
    @Autowired
    private FinalExamService finalExamService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> createFinalExam(@RequestBody FinalExamDto finalExamDto)
    {
        finalExamService.createFinalExam(finalExamDto);
        return new ResponseEntity<>("Examen final creado con éxito", HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'STUDENT', 'PROFESSOR')")
    public ResponseEntity<List<FinalExamDto>> getAllFinalExam()
    {
        return new ResponseEntity<>(finalExamService.getAllFinalExam(), HttpStatus.OK);
    }
}
