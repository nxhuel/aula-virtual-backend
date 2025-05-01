package com.ifts.aula_virtual_backend.controller;

import com.ifts.aula_virtual_backend.dto.SubjectDto;
import com.ifts.aula_virtual_backend.persistence.entity.SubjectEntity;
import com.ifts.aula_virtual_backend.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/api/subject")
@PreAuthorize("denyAll()")
public class SubjectController
{
    @Autowired
    private SubjectService subjectService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'PROFESSOR')")
    public ResponseEntity<Void> createSubject(@RequestBody SubjectEntity subject)
    {
        subjectService.createSubject(subject);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

        @GetMapping
        @PreAuthorize("hasAnyRole('ADMIN', 'PROFESSOR', 'STUDENT')")
        public ResponseEntity<List<SubjectDto>> getAllSubjects()
        {
            List<SubjectDto> subjects = subjectService.getAllSubjects();
            return new ResponseEntity<>(subjects, HttpStatus.OK);
        }

    @GetMapping("/{code}")
    @PreAuthorize("hasAnyRole('ADMIN', 'PROFESSOR', 'STUDENT')")
    public ResponseEntity<Optional<SubjectEntity>> getSubjectByCode(@PathVariable Long code)
    {
        return new ResponseEntity<>(subjectService.getSubjectByCode(code), HttpStatus.OK);
    }

    @GetMapping("/delete/{code}")
    @PreAuthorize("hasAnyRole('ADMIN', 'PROFESSOR')")
    public ResponseEntity<Void> deleteSubjectByCode(@PathVariable Long code)
    {
        subjectService.deleteSubjectByCode(code);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
