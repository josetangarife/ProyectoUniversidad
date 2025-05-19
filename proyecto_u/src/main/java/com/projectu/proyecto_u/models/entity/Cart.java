package com.projectu.proyecto_u.models.entity;

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
@Table(name = "cart")
public class Cart {

    @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column(name = "cart_id")
    private Long cart_id;

    @ManyToOne
    @JoinColumn(name = "cart_customer_id", nullable = false)
    private Customer cart_customer_id;

      @ManyToOne
    @JoinColumn(name = "cart_product_id", nullable = false)
    private Product cart_product_id;

    
    @Column(name = "cart_product_quantity", nullable = false)
    private Integer cart_product_quantity;


    
}
