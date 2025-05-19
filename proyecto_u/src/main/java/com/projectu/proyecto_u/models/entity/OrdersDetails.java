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
@Table(name = "order_details")
public class OrdersDetails {

       @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column(name = "order_details_id")
    private Long order_details_id;

     @ManyToOne
    @JoinColumn(name = "order_details_products", nullable = false)
    private Product order_details_products;

    @Column(name = "order_details_product_quantity", nullable = false)
    private Integer order_details_product_quantity;

     @Column(name="order_details_subtotal_amount", precision = 10, scale = 2)
    private BigDecimal order_details_subtotal_amount;

    
}
