package com.projectu.proyecto_u.controllers;

//Este Enum esta relacionado con los procesos de autenticacion  (auth) por eso su nombre AuthStatus
//Enum es una palabra clave en java que define un tipo enumerado
//un enumerado se utiliza para representar un numero fijo de constantes. cada una con un nombre identificador unico
public enum AuthStatus {
    USER_CREATED_SUCCESSFULLY,
    USER_NOT_CREATED,
    LOGIN_SUCCESS, 
    LOGIN_FAILED
}
