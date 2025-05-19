package com.projectu.proyecto_u.services;

public interface IAuthService {

    
 
    String Login (String user_name, String password);
    String SignUp(String nombre, String user_name, String password, String email, String rol);
    String verifyToken(String token);
}
