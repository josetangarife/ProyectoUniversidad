package com.projectu.proyecto_u.configuration;

import java.util.Arrays;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;



@Configuration // Anotacion que marca esta clase como una clase de configuracion de spring
public class SecurityFilterChainConfig {

    private final AuthenticationEntryPoint authenticationEntryPoint;//Punto de entrada para manejar excepciones de autenticacion
private final JWTAuthenticationFilter jwtAuthenticationFilter; //filtro personalizado para manejas las autenticacion con jwt

//constructor de la clase que inicializa el punto de entrada para manejar excepciones de autenticacion "authenticationEntryPoint"
public SecurityFilterChainConfig(AuthenticationEntryPoint authenticationEntryPoint,JWTAuthenticationFilter jwtAuthenticationFilter  ){
    this.authenticationEntryPoint=authenticationEntryPoint;
    this.jwtAuthenticationFilter=jwtAuthenticationFilter;
}

//Definicion de un bean que configura la seguridad para las solicitudes HTTP

@Bean
//este metodo funciona de la siguiente forma spring revisa la ruta si coincide con algun .requestMatcher(..) if(RequesMarcher.Ruta == Usuario.Peticion.Ruta) sout("Spring no exige autenticación") else sout("spring no detecta coincidencias entonces exige un token Jwt valido")
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //CONFIGURACION DE SEGURIDAD PARA SOLICITUDES HTTP

        //Habilitamos corsConfig para mi metodo personalizado
    http.cors(cors -> cors.configurationSource(corsConfigurationSource()));
       
         //Desabilita el CRSF (CROSS-SITE-REQUEST-FORGERY)
        http.csrf(AbstractHttpConfigurer::disable);
       
         //Configuramos las reglas de autorizaciones para las solicitudes HTTP
        http.authorizeHttpRequests(
            requestMatchers -> requestMatchers
            .requestMatchers("/api/auth/login/**").permitAll()// esto permite el acceso publico sin necesidad de autenticacion y lo que significa despues de la ruta /api/auth/login "/**" esto significa que se permite cualquier agregado a esta ruta tambien sin autenticacion
        .requestMatchers("/api/auth/registrar/**").permitAll()
        .anyRequest().authenticated() // Requiere Autenticacion para cualquier otra Ruta, es decir si el cliente no incluye un token valido en el encabezado authorization, Spring Respondera con error 401
        );

        // Configuracion como manejar excepciones de autenticacion

        http.exceptionHandling(

         exceptionConfig -> exceptionConfig.authenticationEntryPoint(authenticationEntryPoint)
         //utiliza el metodo de entrada personalizado para manejar errores de autenticacion y cada Token sospechoso lo tratara con un 401 UNATHORIZED
        );
        //CONFIGURA LA GESTION DE SESIONES
        http.sessionManagement(
            sessionConfig -> sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            // Configura la politica de sessiones como STATELESS, lo qeu significa que no se utilizaran sesiones de usuario (ideal para apis con JWT) Basicamente Spring no guarda session en el servidor cada solicitud debe incluir un JWT valido
        );
        //Agrega filtro personalizado antes del filtro estandar de autenticación de usuario y contraseña
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

         
        //Devuelve la configuracion construida
        return http.build();
    }

    //Este metodo define las reglas del CORS (CROSS-ORIGIN-RESOURCES-SHARING) es decir que dominios externos pueden acceder al backend
     @Bean
    public CorsConfigurationSource  corsConfigurationSource(){
        
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:5173")); //SetAllowedOrigins significa que permite solicitudes de esta URL donde normalmente suele correr React
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));//setAllowedMethods define que metodos estan permitidos
        configuration.setAllowedHeaders(Arrays.asList("*")); //setAllowedHeaders permiteque usen todos los encabezados
        configuration.setAllowCredentials(true);// permite el uso de cookies o Headers de autenticacion como jason web token(JWT)
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);//aplica estas reglas a todas las rutas del backend
        return source;
    }
}
    

