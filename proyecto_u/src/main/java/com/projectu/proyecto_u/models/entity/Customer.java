package com.projectu.proyecto_u.models.entity;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat; 
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "customer")
public class Customer implements Serializable{
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column(name = "customerid")
    private Long customer_id;

    @Column(name="email", unique=true, nullable = false)
    private String email;

    @Column(name="password", nullable = false)
    private String password;

    

    @Column(name = "username", nullable = false, unique = true, length = 100)
    private String username;
     
    @Column(name="rol", nullable = false, length = 30)
    private String rol;

    @Column(name="nombre", nullable = true)
    private String nombre;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @Column(name = "register_date")
    private LocalDateTime register_date;


}
