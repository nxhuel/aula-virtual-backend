package com.ifts.aula_virtual_backend.persistence.repository;

import com.ifts.aula_virtual_backend.persistence.entity.FinalExamRegisteredEntity;
import com.ifts.aula_virtual_backend.persistence.entity.SubjectInscribedEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FinalExamRegisteredRepository extends JpaRepository<FinalExamRegisteredEntity, Long>
{
    List<FinalExamRegisteredEntity> findAllByStudent_Dni(String dni);


}
