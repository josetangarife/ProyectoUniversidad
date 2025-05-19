package com.projectu.proyecto_u.services.impl;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.projectu.proyecto_u.models.entity.Customer;
import com.projectu.proyecto_u.repository.CustomerRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailsServicesImpl implements UserDetailsService {

    private final CustomerRepo customerRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Buscar el usuario en el repositorio
        var customer = customerRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

        // Construir y retornar el objeto UserDetails usando los datos del usuario
        return User.builder()
                .username(customer.getUsername())
                .password(customer.getPassword())
                .roles(customer.getRol()) // Puedes reemplazarlo con los roles de tu entidad
                .build();
    }
}
