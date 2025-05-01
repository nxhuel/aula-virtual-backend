package com.ifts.aula_virtual_backend;

import com.ifts.aula_virtual_backend.dto.NotesConsultationDto;
import com.ifts.aula_virtual_backend.persistence.entity.*;
import com.ifts.aula_virtual_backend.persistence.repository.SubjectInscribedRepository;
import com.ifts.aula_virtual_backend.persistence.repository.SubjectRepository;
import com.ifts.aula_virtual_backend.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class AulaVirtualBackendApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(AulaVirtualBackendApplication.class, args);
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    CommandLineRunner init(UserRepository userRepository, SubjectRepository subjectRepository, SubjectInscribedRepository subjectInscribedRepository)
    {
        return args ->
        {
//			Permissions
            PermissionEntity createPermission = PermissionEntity.builder()
                    .name("CREATE")
                    .build();

            PermissionEntity readPermission = PermissionEntity.builder()
                    .name("READ")
                    .build();

            PermissionEntity updatePermission = PermissionEntity.builder()
                    .name("UPDATE")
                    .build();

            PermissionEntity deletePermission = PermissionEntity.builder()
                    .name("DELETE")
                    .build();

//            Roles
            RoleEntity roleAdmin = RoleEntity.builder()
                    .roleEnum(RoleEnum.ADMIN)
                    .permissions(Set.of(createPermission, readPermission, updatePermission, deletePermission))
                    .build();

            RoleEntity roleStudent = RoleEntity.builder()
                    .roleEnum(RoleEnum.STUDENT)
                    .permissions(Set.of(readPermission))
                    .build();

            RoleEntity roleProfessor = RoleEntity.builder()
                    .roleEnum(RoleEnum.PROFESSOR)
                    .permissions(Set.of(createPermission, readPermission, updatePermission))
                    .build();

//            Users
            StudentEntity studentSantiago = StudentEntity.builder()
                    .dni("46131227")
                    .username("Santiago")
                    .lastname("Martinez")
                    .password(passwordEncoder.encode("1234"))
                    .isEnabled(true)
                    .accountNoExpired(true)
                    .accountNoLocked(true)
                    .credentialNoExpired(true)
                    .roles(Set.of(roleStudent))
                    .legajo("3898")
                    .build();

            StudentEntity studentDaiana = StudentEntity.builder()
                    .dni("46131226")
                    .username("Daiana")
                    .lastname("Lopez")
                    .password(passwordEncoder.encode("1234"))
                    .isEnabled(true)
                    .accountNoExpired(true)
                    .accountNoLocked(true)
                    .credentialNoExpired(true)
                    .roles(Set.of(roleStudent))
                    .legajo("5898")
                    .build();

            ProfessorEntity professorPablo = ProfessorEntity.builder()
                    .dni("46131228")
                    .username("Pablo")
                    .lastname("Gonzalez")
                    .password(passwordEncoder.encode("1234"))
                    .isEnabled(true)
                    .accountNoExpired(true)
                    .accountNoLocked(true)
                    .credentialNoExpired(true)
                    .roles(Set.of(roleAdmin, roleProfessor))
                    .legajo("7898")
                    .build();

            userRepository.saveAll(List.of(studentSantiago, studentDaiana, professorPablo));
        };
    }

    @Configuration
    public static class Myconfiguration
    {
        @Bean
        public WebMvcConfigurer corsConfigurer()
        {
            return new WebMvcConfigurer()
            {
                @Override
                public void addCorsMappings(CorsRegistry registry)
                {
                    registry.addMapping("/**")
                            .allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH");
                }
            };
        }
    }

}
