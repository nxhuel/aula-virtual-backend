package com.ifts.aula_virtual_backend.controller;

import com.ifts.aula_virtual_backend.dto.PasswordChangeRequestDto;
import com.ifts.aula_virtual_backend.dto.UserLoginResponseDto;
import com.ifts.aula_virtual_backend.persistence.entity.UserEntity;
import com.ifts.aula_virtual_backend.persistence.repository.UserRepository;
import com.ifts.aula_virtual_backend.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/api/auth")
@PreAuthorize("denyAll()")
public class TestAuthController
{

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/user-logged")
    @PreAuthorize("hasAnyRole('ADMIN', 'STUDENT', 'PROFESSOR')")
    public ResponseEntity<UserLoginResponseDto> getUserLogged(Authentication authentication)
    {
        String dni = authentication.getName();
        UserEntity user = userService.findByDni(dni);

        Set<String> roles = user.getRoles()
                .stream()
                .map(role -> {
                    switch (role.getRoleEnum().name()) {
                        case "STUDENT": return "STUDENT";
                        case "ADMIN": return "ADMIN";
                        case "PROFESSOR": return "PROFESSOR";
                        default: return "OTRO";
                    }
                })
                .collect(Collectors.toSet());

        UserLoginResponseDto userLogged = new UserLoginResponseDto();
        userLogged.setDni(user.getDni());
        userLogged.setUsername(user.getUsername());
        userLogged.setLastname(user.getLastname());
        userLogged.setRoles(roles);

        return new ResponseEntity<>(userLogged, HttpStatus.OK);
    }

    @PutMapping("/change-password")
    @PreAuthorize("hasAnyRole('ADMIN', 'STUDENT', 'PROFESSOR')")
    public ResponseEntity<?> changePassword(
            @RequestBody PasswordChangeRequestDto request,
            @AuthenticationPrincipal UserDetails userDetails) {

        String dni = userDetails.getUsername();
        UserEntity user = userService.findByDni(dni);

        if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Clave actual incorrecta");
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);

        return ResponseEntity.ok("Contraseña actualizada");
    }
}
