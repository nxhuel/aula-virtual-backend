package com.ifts.aula_virtual_backend.controller;

import com.ifts.aula_virtual_backend.dto.FinalExamRegisteredDto;
import com.ifts.aula_virtual_backend.dto.FinalExamRegisteredResponseDto;
import com.ifts.aula_virtual_backend.service.FinalExamRegisteredService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/final-exam-registrations")
@PreAuthorize("denyAll()")
public class FinalExamRegisteredController
{

    @Autowired
    private FinalExamRegisteredService finalExamRegisteredService;

    @PostMapping
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<String> registerToFinalExam(@RequestBody FinalExamRegisteredDto finalExamRegisteredDto,
                                                      Authentication authentication) {
        String dni = authentication.getName();
        finalExamRegisteredService.registerToFinalExam(finalExamRegisteredDto, dni);
        return ResponseEntity.ok("Inscripción al examen final registrada correctamente");
    }

    @GetMapping("/my-registrations")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<List<FinalExamRegisteredResponseDto>> getMyFinalExamRegistrations(
            Authentication authentication) {
        String dni = authentication.getName();
        List<FinalExamRegisteredResponseDto> exams = finalExamRegisteredService.getMyFinalExamRegistrations(dni);
        return new ResponseEntity<>(exams, HttpStatus.OK);
    }

}
