package com.ifts.aula_virtual_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CoursesConsultationDto
{
    private Long subjectInscribedId;
    private String registrationNumber;
    private Character period;
    private String name;
    private String professorName;
    private String professorLastname;
}

