package com.ifts.aula_virtual_backend.service.impl;

import com.ifts.aula_virtual_backend.dto.ProfessorRequestDto;
import com.ifts.aula_virtual_backend.dto.StudentRequestDto;
import com.ifts.aula_virtual_backend.persistence.entity.*;
import com.ifts.aula_virtual_backend.persistence.repository.ProgramRepository;
import com.ifts.aula_virtual_backend.persistence.repository.RoleRepository;
import com.ifts.aula_virtual_backend.persistence.repository.UserRepository;
import com.ifts.aula_virtual_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService
{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ProgramRepository programRepository;

    @Override
    public UserEntity findByDni(String dni)
    {
        return userRepository.findUserEntityByDni(dni)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
    }

    @Override
    public StudentEntity createStudent(StudentRequestDto student)
    {
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setDni(student.getDni());
        studentEntity.setLegajo(student.getLegajo());
        studentEntity.setUsername(student.getUsername());
        studentEntity.setLastname(student.getLastname());
        studentEntity.setPassword(passwordEncoder.encode(student.getPassword()));

        studentEntity.setIsEnabled(true);
        studentEntity.setAccountNoExpired(true);
        studentEntity.setAccountNoLocked(true);
        studentEntity.setCredentialNoExpired(true);

        Set<RoleEntity> roles = new HashSet<>();
        for (Long roleId : student.getRoleId()) {
            RoleEntity role = roleRepository.findById(roleId)
                    .orElseThrow(() -> new RuntimeException("Rol no encontrado con ID: " + roleId));
            roles.add(role);
        }
        studentEntity.setRoles(roles);


        AcademicPlanEnum planEnum;
        try {
            planEnum = AcademicPlanEnum.valueOf(student.getAcademicPlan());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Carrera inválida: " + student.getAcademicPlan());
        }

        ProgramEntity program = programRepository.findByAcademicPlan(planEnum)
                .orElseThrow(() -> new RuntimeException("No se encontró el plan académico"));

        studentEntity.setProgram(program);

        return userRepository.save(studentEntity);
    }

    @Override
    public ProfessorEntity createProfessor(ProfessorRequestDto professor)
    {
        ProfessorEntity professorEntity = new ProfessorEntity();
        professorEntity.setDni(professor.getDni());
        professorEntity.setLegajo(professor.getLegajo());
        professorEntity.setUsername(professor.getUsername());
        professorEntity.setLastname(professor.getLastname());
        professorEntity.setPassword(passwordEncoder.encode(professor.getPassword()));
        professorEntity.setIsEnabled(true);
        professorEntity.setAccountNoExpired(true);
        professorEntity.setAccountNoLocked(true);
        professorEntity.setCredentialNoExpired(true);

        Set<RoleEntity> roles = new HashSet<>();
        for (Long roleId : professor.getRoleId()) {
            RoleEntity role = roleRepository.findById(roleId)
                    .orElseThrow(() -> new RuntimeException("Rol no encontrado con ID: " + roleId));
            roles.add(role);
        }
        professorEntity.setRoles(roles);
        professorEntity.setProfessorTitle(professor.getProfessorTitle());
        professorEntity.setContract(professor.getContract());
        professorEntity.setContractPdf(professor.getContractPdf());

        return userRepository.save(professorEntity);
    }
}
