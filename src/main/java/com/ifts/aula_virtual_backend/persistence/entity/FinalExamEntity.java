package com.ifts.aula_virtual_backend.persistence.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "final_exam")
public class FinalExamEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "academic_plan")
    private AcademicPlanEnum academicPlan;

    @ManyToOne(targetEntity = SubjectEntity.class)
    private SubjectEntity subject;

    @Column(name = "final_date")
    private LocalDate finalDate;
}
