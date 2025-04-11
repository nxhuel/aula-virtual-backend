package com.ifts.aula_virtual_backend.service.impl;

import com.ifts.aula_virtual_backend.persistence.entity.UserEntity;
import com.ifts.aula_virtual_backend.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailServiceImpl implements UserDetailsService
{

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String dni) throws UsernameNotFoundException
    {
        UserEntity user = userRepository.findUserEntityByDni(dni)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario " + dni + " no existe."));

        if (!user.getIsEnabled())
        {
            throw new UsernameNotFoundException("El usuario " + dni + " no existe.");
        }

        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();

        user.getRoles()
                .forEach(role -> authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(role.getRoleEnum().name()))));

        user.getRoles().stream()
                .flatMap(role -> role.getPermissions().stream())
                .forEach(permission -> authorityList.add(new SimpleGrantedAuthority(permission.getName())));

        return new User(user.getDni(),
                user.getPassword(),
                user.getIsEnabled(),
                user.getAccountNoExpired(),
                user.getCredentialNoExpired(),
                user.getAccountNoLocked(),
                authorityList);
    }
}
