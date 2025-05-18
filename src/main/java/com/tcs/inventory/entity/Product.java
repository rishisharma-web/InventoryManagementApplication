package com.tcs.inventory.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true)
    private String productCode;
    
    private String name;
    private String description;
    private BigDecimal basePrice;
    private BigDecimal sellingPrice;
    private Integer quantity=0;
    @ManyToOne
    @JoinColumn(name = "inventory_id")
    private Inventory inventory;
}