package com.ifts.aula_virtual_backend.service;

import com.ifts.aula_virtual_backend.persistence.entity.UserEntity;

public interface UserService
{
    UserEntity findByDni(String dni);
}
