package com.projectu.proyecto_u.util;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;



import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwtUtils {

    // Generación de clave secreta (puedes usar una clave más segura en producción)
    private static final SecretKey secretKey = Keys.hmacShaKeyFor("mySuperSecretKey12345678901234567890".getBytes());
    private static final String ISSUER = "server";

    private JwtUtils() {}

    // Validación del token JWT
    public static boolean validateToken(String jwtToken) {
        try {
            Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(jwtToken);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            log.error("JWT Exception occurred: {}", e.getMessage());
            return false;
        }
    }

    // Obtener el nombre de usuario del token
    public static Optional<String> getUsernameFromToken(String jwtToken) {
        try {
            Claims claims = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(jwtToken)
                .getBody();
            String username = claims.getSubject();
            return Optional.ofNullable(username);
        } catch (JwtException | IllegalArgumentException e) {
            log.error("Error parsing JWT token: {}", e.getMessage());
            return Optional.empty();
        }
    }
    public static String generateToken(String username) {
    var currentDay = new Date();
    var jwtExpirationInMinutes = 10;

    // Crear la fecha de expiración usando Calendar
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(currentDay);
    calendar.add(Calendar.MINUTE, jwtExpirationInMinutes);
    var expiration = calendar.getTime();

    // Generar el token JWT
    return Jwts.builder()
            .setId(UUID.randomUUID().toString())          // ID del token
            .setIssuer(ISSUER)                           // Emisor
            .setSubject(username)                        // Usuario
            .setIssuedAt(currentDay)                     // Fecha de emisión
            .setExpiration(expiration)                   // Fecha de expiración
            .signWith(secretKey)                         // Firma con clave secreta
            .compact();                                  // Construcción final del token
}
}
