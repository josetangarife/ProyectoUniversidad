package com.projectu.proyecto_u.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/*Esta clase hace parte de la configuracion de Spring Security especificamente en la autorizacion*/


@Configuration //le dice a spring que esta clase tiene beans de configuracion
public class AuthenticationConfig {

    @Bean
    //AuthenticationManager es un componente que tiene un papel principal en Spring security que se encarga de manejar el proceso de autenticacion completo
    public AuthenticationManager authenticationManager  (AuthenticationConfiguration config) throws Exception  {  
        return config.getAuthenticationManager();
        //Spring me da acceso a La configuracion del Authentication y extraemos al Authentication manager ya configurado internamete, lo exponemos en un bean ¿por qué? para inyectarlo en mis controladores o servicios
}
   @Bean 
   //AuthenticationProvider es quien valida las credenciales del usuario ej(usuario , contraseña)
   public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder){
   //DaoAuthenticationProvider -> basicamente significa: voy a consultar la informacion del usuario requerido en la base de datos atravez de un UserDetails
   //UserDetails le dice al Dao como buscar el usuario requerido ya sea por correo, userName
    var AuthenticationProvider = new DaoAuthenticationProvider();
    AuthenticationProvider.setUserDetailsService(userDetailsService);//Busca el usuario buscando UserDetailsService
    AuthenticationProvider.setPasswordEncoder(passwordEncoder);// compara la contraseña ingresada con la contraseña almacenada en la bd
    //SetPasswordEncoder le dice como comparar la contraseña encryptada por BCrypt

    //si todo coincide la autenticacion es exitosa :)

    return AuthenticationProvider;

   }
   @Bean
   //este metodo crea un password Encoder es decir un componente que cifra contraseñas al hacer registro
   //y me ayuda comparar contraseñas al hacer un login
   public PasswordEncoder bCryptPasswordEncoder(){
    //usa el algoritmo Bcrypt que es seguro, lento y resistentes a ataques de fuerza bruta
    return new BCryptPasswordEncoder();
   }

    
}


/*
 *   GRAFICO DE COMO FUNCIONA ESTA CLASE :
 * 
                                       [Usuario envía email y password]
                                                |
                                                v
                                     [AuthenticationManager]
                                                |
                                                v
                                     [DaoAuthenticationProvider]
                                          |                     |
                                          v                     v
                                     [UserDetailsService]   [PasswordEncoder]
                                          |                     |
                                          |--- busca usuario    |
                                          |                     |
                                          |--- compara contraseñas encriptadas
                                                |
                                                v
                                         [Autenticación Exitosa]

 */
