package com.projectu.proyecto_u.controllers;


import lombok.RequiredArgsConstructor;

public record AuthResponseDto (
    String token, 
    AuthStatus authstatus
){ }
