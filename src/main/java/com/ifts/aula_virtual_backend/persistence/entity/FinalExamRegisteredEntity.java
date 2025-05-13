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
@Table(name = "final_exam_registered")
public class FinalExamRegisteredEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "registration_number")
    private Integer registrationNumber;

    @ManyToOne
    @JoinColumn(name = "id_subject_inscribed")
    private SubjectInscribedEntity subjectInscribed;

    @ManyToOne
    @JoinColumn(name = "final_exam_id")
    private FinalExamEntity finalExam;

    @ManyToOne
    @JoinColumn(name = "id_professor")
    private ProfessorEntity professor;

    @Column(name = "registration_date")
    private LocalDate registrationDate;

    @ManyToOne(targetEntity = StudentEntity.class)
    private StudentEntity student;
}
