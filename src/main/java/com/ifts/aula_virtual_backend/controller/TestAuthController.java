package com.ifts.aula_virtual_backend.controller;

import com.ifts.aula_virtual_backend.dto.UserLoginResponseDto;
import com.ifts.aula_virtual_backend.persistence.entity.UserEntity;
import com.ifts.aula_virtual_backend.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/auth")
@PreAuthorize("denyAll()")
public class TestAuthController
{

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/user-logged")
    @PreAuthorize("hasAnyRole('ADMIN', 'STUDENT')")
    public ResponseEntity<UserLoginResponseDto> getUserLogged(Authentication authentication)
    {
        String dni = authentication.getName();
        UserEntity user = userService.findByDni(dni);

        UserLoginResponseDto userLogged = new UserLoginResponseDto();
        userLogged.setDni(user.getDni());
        userLogged.setUsername(user.getUsername());
        userLogged.setLastname(user.getLastname());

        return new ResponseEntity<>(userLogged, HttpStatus.OK);
    }


    @GetMapping("/get")
    @PreAuthorize("hasAnyRole('ADMIN', 'PROFESSOR', 'STUDENT')")
    public String helloGet()
    {
        return "Hello World - GET";
    }

    @PostMapping("/post")
    @PreAuthorize("hasAnyRole('ADMIN', 'PROFESSOR', 'STUDENT')")
    public String helloPost()
    {
        return "Hello World - POST";
    }

    @PutMapping("/put")
    @PreAuthorize("hasAnyRole('ADMIN', 'PROFESSOR')")
    public String helloPut()
    {
        return "Hello World - PUT";
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasAnyRole('ADMIN', 'PROFESSOR')")
    public String helloDelete()
    {
        return "Hello World - DELETE";
    }

    @PatchMapping("/patch")
    @PreAuthorize("hasRole('ADMIN')")
    public String helloPatch()
    {
        return "Hello World - PATCH";
    }
}
