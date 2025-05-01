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
@Table(name = "subject_inscribed")
public class SubjectInscribedEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "code_subject", nullable = false)
    private SubjectEntity subject;

    @Column(name = "registration_date")
    private LocalDate registrationDate;

    @Column(name = "final_course_grade")
    private Integer finalCourseGrade;

    @Column(name = "final_exam_grade")
    private Integer finalExamGrade;

    @ManyToOne(targetEntity = StudentEntity.class)
    @JoinColumn(name = "id_student", nullable = false)
    private StudentEntity student;

    @ManyToOne(targetEntity = AcademicHistoryEntity.class)
    private AcademicHistoryEntity academic_history;

    @PrePersist
    public void prePersist() {
        this.registrationDate = LocalDate.now();
    }
}
