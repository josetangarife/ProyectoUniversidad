package com.projectu.proyecto_u.controllers;
//que es record ? un record es una clase concisa e inmutable con los metodos basicos para transportar datos como un DTO
//tiene todos los metodos para obtener y setear los datos 
//al declararse la clase de tipo Record automaticamente java, crea los contructores, los getters and setters, metodos como el equals(), hasCode(), toString()
//todos los campos de esta clase son final (no se pueden modificar )
// final ? se refiere a constante o variable que su valor no puede ser modificado despues de inicializarlo
public record AuthRequestDto(
    String nombre,
    String username,
    String password,
    String email,
    String rol

) {
} 

/*GRAFICO DE AuthRequestDto :
                            JSON de la solicitud HTTP
                                    ↓
                            ┌───────────────────────────────────────┐
                            │           AuthRequestDto              │
                            │  (Record generado automáticamente)    │
                            ├───────────────────────────────────────┤
                            │ String nombre                         │
                            │ String username                       │
                            │ String password                       │
                            │ String email                          │
                            │ String rol                            │
                            └───────────────────────────────────────┘
                                    ↓
                            Usado por AuthController → login() y signUp()
 */