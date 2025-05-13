package com.ifts.aula_virtual_backend.persistence.repository;

import com.ifts.aula_virtual_backend.persistence.entity.FinalExamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface FinalExamRepository extends JpaRepository<FinalExamEntity, Long>
{
    Optional<FinalExamEntity> findByFinalDateAndSubject_Name(LocalDate finalDate, String subject);
}
