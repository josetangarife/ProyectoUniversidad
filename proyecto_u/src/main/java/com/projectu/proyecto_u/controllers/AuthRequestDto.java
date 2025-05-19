package com.projectu.proyecto_u.controllers;

public record AuthRequestDto(
    String nombre,
    String username,
    String password,
    String email,
    String rol

) {
} 