package com.ifts.aula_virtual_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NotesUploadDto
{
    private Long subjectInscribedId;
    private Integer finalCourseGrade;
    private Integer finalExamGrade;
}
