package com.projectu.proyecto_u.controllers;


//como se Utiliza esta clase es retornada como respuesta en los metodos AunthController
public record AuthResponseDto (
    String token, //este Campo representa un JWT (json web token) que se genera si el login o registro fue exitoso
    AuthStatus authstatus //esto indica un estado de proceso de autentication
){ }


/*REPRESENTACION GRAFICA DE LA CLASE :
                                     ┌────────────────────────────────────┐
                                     │        AuthResponseDto (record)    │
                                     ├────────────────────────────────────┤
                                     │ token       → String (JWT)         │
                                     │ authstatus  → Enum AuthStatus      │
                                     └────────────────────────────────────┘
                                     
                                     ╭──────────────────────────────╮
                                     │ Cliente recibe:              │
                                     │  {                           │
                                     │   "token": "...",            │
                                     │"authstatus": "LOGIN_SUCCESS" │
                                     │  }                           │
                                     ╰──────────────────────────────╯
                                     
 * 
 * 
 * 
 * 
 */
