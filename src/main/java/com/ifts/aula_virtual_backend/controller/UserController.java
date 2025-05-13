package com.ifts.aula_virtual_backend.controller;

import com.ifts.aula_virtual_backend.dto.ProfessorRequestDto;
import com.ifts.aula_virtual_backend.dto.StudentRequestDto;
import com.ifts.aula_virtual_backend.persistence.entity.ProfessorEntity;
import com.ifts.aula_virtual_backend.persistence.entity.StudentEntity;
import com.ifts.aula_virtual_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/user")
@PreAuthorize("denyAll()")
public class UserController
{
    @Autowired
    private UserService userService;

    @PostMapping("/student")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<StudentEntity> createStudent(@RequestBody StudentRequestDto student)
    {
        StudentEntity studentEntity = userService.createStudent(student);
        return new ResponseEntity<>(studentEntity, HttpStatus.CREATED);
    }

    @PostMapping("/professor")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProfessorEntity> createProfessor(@RequestBody ProfessorRequestDto professor)
    {
        ProfessorEntity professorEntity = userService.createProfessor(professor);
        return new ResponseEntity<>(professorEntity, HttpStatus.CREATED);
    }
}
