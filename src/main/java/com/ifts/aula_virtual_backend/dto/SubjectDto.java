package com.ifts.aula_virtual_backend.dto;

import com.ifts.aula_virtual_backend.persistence.entity.AcademicPlanEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubjectDto
{
    private Long code;
    private AcademicPlanEnum academicPlan;
    private String name;
    private Character year;
    private Character period;
    private String professorName;
    private String professorLastname;
}
