package com.ifts.aula_virtual_backend.persistence.repository;

import com.ifts.aula_virtual_backend.persistence.entity.StudentEntity;
import com.ifts.aula_virtual_backend.persistence.entity.SubjectEntity;
import com.ifts.aula_virtual_backend.persistence.entity.SubjectInscribedEntity;
import com.ifts.aula_virtual_backend.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubjectInscribedRepository extends JpaRepository<SubjectInscribedEntity, Long>
{
    List<SubjectInscribedEntity> findByStudentDni(String dni);

    boolean existsByStudentAndSubject(StudentEntity student, SubjectEntity subject);

    Optional<SubjectInscribedEntity> findByStudentAndSubject_Name(UserEntity student, String subjectName);

    Optional<SubjectInscribedEntity> findByStudent_DniAndSubject_Name(String dni, String subject);
}
