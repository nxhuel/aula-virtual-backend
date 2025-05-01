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

    @OneToOne(targetEntity = SubjectInscribedEntity.class, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_subject_iscribed")
    private SubjectInscribedEntity subjectInscribed;

    @Column(name = "final_date")
    private LocalDate finalDate;

    @OneToOne(targetEntity = ProfessorEntity.class, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_professor")
    private ProfessorEntity professor;

    @Column(name = "registration_date")
    private LocalDate registrationDate;

    @ManyToOne(targetEntity = StudentEntity.class)
    private StudentEntity student;
}
