package com.projectu.proyecto_u.services.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.projectu.proyecto_u.models.entity.Customer;
import com.projectu.proyecto_u.repository.CustomerRepo;
import com.projectu.proyecto_u.services.IAuthService;
import com.projectu.proyecto_u.util.JwtUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomerRepo customerRepo;

    @Override
    public String Login(String username, String password) {
        try {
            var authToken = new UsernamePasswordAuthenticationToken(username, password);
            var authenticate = authenticationManager.authenticate(authToken);
            
            // Obtener el nombre de usuario del objeto UserDetails
            UserDetails userDetails = (UserDetails) authenticate.getPrincipal();
            return JwtUtils.generateToken(userDetails.getUsername());
        } catch (Exception e) {
            throw new RuntimeException("Error de autenticación: " + e.getMessage());
        }
    }

    @Override
    public String SignUp(String nombre, String username, String password, String email, String rol) {
        if (customerRepo.existsByUsername(username)) {
            throw new RuntimeException("El nombre de usuario ya existe");
        }
        if (customerRepo.existsByEmail(email)) {
            throw new RuntimeException("El correo electrónico ya existe");
        }

        // Crear un nuevo objeto Customer
        Customer customer = new Customer();
        customer.setUsername(username);
        customer.setPassword(passwordEncoder.encode(password));  // Codificación de la contraseña
        customer.setNombre(nombre);
        customer.setEmail(email);
        customer.setRol(rol);
        customer.setRegister_date(LocalDateTime.now());

        // Guardar en la base de datos
        customerRepo.save(customer);
        System.out.println("Usuario Guardado: " + customer.getUsername());

        // Generar y devolver el token
        return JwtUtils.generateToken(username);
    }


    @Override
    public String verifyToken(String token) {
        var usernameOptional = JwtUtils.getUsernameFromToken(token);
        if (usernameOptional.isPresent()) {
            return usernameOptional.get();
        } else {
            throw new RuntimeException("Token inválido");
        }
    }
}
