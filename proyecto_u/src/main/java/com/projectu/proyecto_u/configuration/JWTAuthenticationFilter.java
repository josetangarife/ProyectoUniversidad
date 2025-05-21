package com.projectu.proyecto_u.configuration;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.projectu.proyecto_u.util.JwtUtils;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
// esta clase es clave porque se encarga de validar el token en cada solicitud Cliente

@Component//Spring detecta esta clase como componente entonces la ejecutara cada vez que hatya una solicitud HTTP
public class JWTAuthenticationFilter extends OncePerRequestFilter {
//asegura que esta clase se ejecute solo una vez por cada solicitud


    @Autowired //AutoWired es una anotacion que nos ayuda simplificar la inyeccion de dependencias en la clase
    private UserDetailsService userDetailsService;//inyeccion de dependencias 
    //Spring utiizara esta inyeccion de dependencias para cargar los datos de usuarios desde la base de datos 

    //se usa despues de verificar el JWT

    @Override
    //recordemos OncePerRequestFilter 
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) 
            throws ServletException, IOException {

        var jwtTokenOptional = getTokenFromRequest(request);// paso 1. obtiene el token del encabezado

        jwtTokenOptional.ifPresent(jwtToken -> {
            if (JwtUtils.validateToken(jwtToken)) {
                var usernameOptional = JwtUtils.getUsernameFromToken(jwtToken);//paso 2. validar si el token existe

                //paso 3. extraer el UserNamame el token contiene el nombre del usuario, el token se extrae para cargar el usuario desde la base de datos 
                usernameOptional.ifPresent(username -> {
                    var userDetails = userDetailsService.loadUserByUsername(username);//loadByUsername : carga datos del Usuario(email, roles, etc..)

                    //paso 4 cargar el usuario 
                    //UsernamePasswordAuthenticationToken : crea un "token interno " que representa que ese usuario ya esta autenticado
                    var authenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );
                    authenticationToken.setDetails( //setDetails añade detalles del cliente como su ip o navegador
                            new WebAuthenticationDetailsSource().buildDetails(request)
                    );
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);//SecurtyContextHolder : Spring guarda aqui el usuario autenticado para que lo reconozca durante la ejecucion
                });
            }
        });

        //paso 5. Despues de realizar todo lo anterior se pasa la solicitud al siguiente filtro (o controlador rest)
        filterChain.doFilter(request, response);
    }

    // Método para obtener el token del encabezado de la solicitud
    private Optional<String> getTokenFromRequest(HttpServletRequest request) {
        var authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);//getHeader lee el encabezado authorization de la solicitud
        if (StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")) {           //starWith("bearer") verifica que el token sea tipo bearer (jwt standar)

        //return optional retorna un objeto que puede tener valor o estar vacio
            return Optional.of(authHeader.substring(7));
        }
        return Optional.empty();
    }
}

/*GRAFICO COMO FUNCIONA LA CLASE : 
                                 [Cliente envía solicitud HTTP con token JWT]
                                                 |
                                                 v
                                 [JWTAuthenticationFilter]  <-- (se ejecuta automáticamente)
                                       |
                                       |---> Extrae token del header
                                       |---> Valida firma y expiración
                                       |---> Extrae nombre de usuario
                                       |---> Carga usuario desde BD
                                       |---> Marca la solicitud como autenticada
                                       |
                                       v
                                 [Controlador REST recibe la solicitud con el usuario ya autenticado]
  
 */
