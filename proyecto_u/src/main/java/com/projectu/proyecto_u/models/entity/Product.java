package com.projectu.proyecto_u.models.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "product")
public class Product {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column(name = "product_id")
    private Long product_id;

    @ManyToOne
    @JoinColumn(name = "product_workshop_id", nullable = false)
    private Workshop product_workshop_id;

     @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name="description", nullable = false, length = 150)
    private String description;

    @Column(name="product_category", nullable = false, length=30)
    private String product_category ;

    @Column(name="price", precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name="stock", nullable = false)
    private Integer stock;
    
}
