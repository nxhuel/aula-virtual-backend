package com.ifts.aula_virtual_backend.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "academic_history")
public class AcademicHistoryEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(targetEntity = SubjectInscribedEntity.class, fetch = FetchType.LAZY, mappedBy = "academic_history")
    private Set<SubjectInscribedEntity> subjectInscribed = new HashSet<>();
}
