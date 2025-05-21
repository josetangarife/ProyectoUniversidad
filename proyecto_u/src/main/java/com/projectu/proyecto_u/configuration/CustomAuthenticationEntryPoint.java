package com.projectu.proyecto_u.configuration;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
/* 
    @Param request  La solicitud http ENTRANTE
    @Param response La resuesta Http que se enviara al cliente
    @param authException La excepcion que se genero debido la falta de autenticacion

    @Throws IOException si ocurre un error al escribitr en la respuesta.
    @throws servletExcetion si ocurre un error relacinado con el servlet
*/


    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {
        
        // Enviar el código de error 401 (No autorizado) con el mensaje como respuesta al cliente, indicando que no tiene permisos para acceder al recurso solicitado
        response.sendError(HttpStatus.UNAUTHORIZED.value(), "Acceso denegado: " + authException.getMessage());
    }
}
