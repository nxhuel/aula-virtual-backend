package com.ifts.aula_virtual_backend.dto;

import com.ifts.aula_virtual_backend.persistence.entity.RoleEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginResponseDto
{
    private String dni;
    private String username;
    private String lastname;
    private Set<String> roles;
}
