package com.projectu.proyecto_u.models.entity;

import java.math.BigDecimal;
import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

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
@Table(name = "order")
public class Order {

    @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column(name = "order_id")
    private Long order_id;

    @ManyToOne
    @JoinColumn(name = "order_customer_id", nullable = false)
    private Customer order_customer_id;

    @ManyToOne
    @JoinColumn(name = "order_workshop_id", nullable = false)
    private Workshop order_workshop_id;

     @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @Column(name = "order_date")
    private Date order_date;

      @Column(name="total_amount", precision = 10, scale = 2)
    private BigDecimal total_amount;

    
}
