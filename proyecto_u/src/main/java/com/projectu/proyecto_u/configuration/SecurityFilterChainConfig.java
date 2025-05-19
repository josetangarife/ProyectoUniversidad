package com.projectu.proyecto_u.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityFilterChainConfig {

    private final AuthenticationEntryPoint authenticationEntryPoint;
private final JWTAuthenticationFilter jwtAuthenticationFilter;

public SecurityFilterChainConfig(AuthenticationEntryPoint authenticationEntryPoint,JWTAuthenticationFilter jwtAuthenticationFilter  ){
    this.authenticationEntryPoint=authenticationEntryPoint;
    this.jwtAuthenticationFilter=jwtAuthenticationFilter;
}


@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
       
        http.cors(corsConfig -> corsConfig.disable());
       
        http.csrf(AbstractHttpConfigurer::disable);

        http.authorizeHttpRequests(
            requestMatchers -> requestMatchers
            .requestMatchers("/api/auth/login/**").permitAll()
        .requestMatchers("/api/auth/registrar/**").permitAll()
        .anyRequest().authenticated()
        );

        http.exceptionHandling(

         exceptionConfig -> exceptionConfig.authenticationEntryPoint(authenticationEntryPoint)
        );

        http.sessionManagement(
            sessionConfig -> sessionConfig.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
        );
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

         

        return http.build();
    }
}
    

