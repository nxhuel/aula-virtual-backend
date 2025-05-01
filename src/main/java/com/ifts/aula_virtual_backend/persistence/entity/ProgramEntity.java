package com.ifts.aula_virtual_backend.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import lombok.Builder;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "program")
public class ProgramEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;

    @Column(name = "academic_plan")
    private AcademicPlanEnum academicPlan;

    @Column(name = "duration")
    private Double duration;

    @Column(name = "study_plan_pdf")
    private String studyPlanPdf;
}
