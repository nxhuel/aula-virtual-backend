package com.ifts.aula_virtual_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NotesConsultationDto
{
    private Long subjectInscribedId;
    private Character year;
    private String subject;
    private LocalDate startDateCourse;
    private Integer finalCourseGrade;
    private LocalDate finalDateCourse;
    private Integer finalExamGrade;
}
