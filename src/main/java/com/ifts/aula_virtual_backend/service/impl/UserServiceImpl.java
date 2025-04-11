package com.ifts.aula_virtual_backend.service.impl;

import com.ifts.aula_virtual_backend.persistence.entity.UserEntity;
import com.ifts.aula_virtual_backend.persistence.repository.UserRepository;
import com.ifts.aula_virtual_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService
{
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserEntity findByDni(String dni)
    {
        return userRepository.findUserEntityByDni(dni)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
    }
}
