package com.ifts.aula_virtual_backend.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import lombok.Builder;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "subject")
public class SubjectEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;

    @Column(name = "name")
    private String name;

    @Column(name = "year")
    private Character year;

    @Column(name = "period")
    private Character period;

    @Column(name = "start_date_course")
    private LocalDate startDateCourse;

    @Column(name = "final_date_course")
    private LocalDate finalDateCourse;

    @ManyToOne(targetEntity = ProfessorEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_professor")
    private ProfessorEntity professor;
}
