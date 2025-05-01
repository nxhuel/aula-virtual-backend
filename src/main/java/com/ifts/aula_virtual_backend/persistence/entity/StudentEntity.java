package com.ifts.aula_virtual_backend.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DiscriminatorValue("STUDENT")
public class StudentEntity extends UserEntity
{
    @Column(unique = true)
    private String legajo;

    @OneToOne(targetEntity = ProgramEntity.class, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "code_program")
    private ProgramEntity program;

    @OneToMany(targetEntity = SubjectInscribedEntity.class, fetch = FetchType.LAZY, mappedBy = "student")
    private List<SubjectInscribedEntity> subjectInscribedEntities = new ArrayList<>();

    @OneToMany(targetEntity = FinalExamRegisteredEntity.class, fetch = FetchType.LAZY, mappedBy = "student")
    private List<FinalExamRegisteredEntity> finalExamRegistered = new ArrayList<>();

    @OneToOne(targetEntity = AcademicHistoryEntity.class, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_academic_history")
    private AcademicHistoryEntity academicHistory;
}
