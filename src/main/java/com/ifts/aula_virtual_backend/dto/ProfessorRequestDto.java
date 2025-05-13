package com.ifts.aula_virtual_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfessorRequestDto
{
    private String dni;
    private String legajo;
    private String username;
    private String lastname;
    private String password;
    private Set<Long> roleId;
    private String professorTitle;
    private String contract;
    private String contractPdf;
}
