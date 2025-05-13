package com.ifts.aula_virtual_backend.persistence.repository;

import com.ifts.aula_virtual_backend.persistence.entity.AcademicPlanEnum;
import com.ifts.aula_virtual_backend.persistence.entity.ProgramEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProgramRepository extends JpaRepository<ProgramEntity, Long>
{
    Optional<ProgramEntity> findByAcademicPlan(AcademicPlanEnum plan);

}
