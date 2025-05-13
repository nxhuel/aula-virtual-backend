package com.ifts.aula_virtual_backend.persistence.repository;

import com.ifts.aula_virtual_backend.persistence.entity.SubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubjectRepository extends JpaRepository<SubjectEntity, Long>
{
    Optional<SubjectEntity> findByName(String subject);
}
