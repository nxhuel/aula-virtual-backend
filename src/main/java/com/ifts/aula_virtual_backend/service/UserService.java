package com.ifts.aula_virtual_backend.service;

import com.ifts.aula_virtual_backend.dto.ProfessorRequestDto;
import com.ifts.aula_virtual_backend.dto.StudentRequestDto;
import com.ifts.aula_virtual_backend.persistence.entity.ProfessorEntity;
import com.ifts.aula_virtual_backend.persistence.entity.StudentEntity;
import com.ifts.aula_virtual_backend.persistence.entity.UserEntity;

public interface UserService
{
    UserEntity findByDni(String dni);

    StudentEntity createStudent(StudentRequestDto student);

    ProfessorEntity createProfessor(ProfessorRequestDto professor);
}
