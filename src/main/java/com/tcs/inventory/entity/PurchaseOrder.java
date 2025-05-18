package com.tcs.inventory.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.tcs.inventory.enums.POStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "purchase_orders")
public class PurchaseOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String poNumber;
    
    @ManyToOne
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;
    
    private LocalDate orderDate;
    private LocalDate expectedDeliveryDate;
    
    @Enumerated(EnumType.STRING)
    private POStatus status;
    
    @OneToMany(mappedBy = "purchaseOrder", cascade = CascadeType.ALL)
    private List<PurchaseOrderItem> items;
    
    private BigDecimal totalAmount;
   
}