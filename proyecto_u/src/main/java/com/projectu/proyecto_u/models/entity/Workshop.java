package com.projectu.proyecto_u.models.entity;

import java.io.Serializable;
import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.*;
import jakarta.persistence.Column;
@Entity 
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "workshop")
public class Workshop implements Serializable {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column(name = "workshop_id")
    private Long workshop_id;

    @Column(name = "rut", nullable = false, unique = true, length = 12)
    private String rut;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "rol", nullable = false, length = 30)
    private String rol;

    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @Column(name = "register_date")
    private Date registerDate ;
    
    //los datos de abajo son certificados aun no lo vamos a integrar
    @Lob
    @Column(name ="bank_certificate" )
    private byte[] certificadoBancario;

    @Lob
    @Column(name ="legal_representative_id_document" )
    private byte[] documentoIdRepresentante;

    @Lob
    @Column(name ="commercial_registration" )
    private byte[] matriculaMercantil;

    


}
