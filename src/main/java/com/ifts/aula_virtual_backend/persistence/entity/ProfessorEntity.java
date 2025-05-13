package com.ifts.aula_virtual_backend.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DiscriminatorValue("PROFESSOR")
public class ProfessorEntity extends UserEntity
{
    @Column(unique = true)
    private String legajo;

    @Column(name = "professor_title")
    private String professorTitle;

    @Column(name = "contract")
    private String contract;

    @Column(name = "contract_pdf")
    private String contractPdf;
}
