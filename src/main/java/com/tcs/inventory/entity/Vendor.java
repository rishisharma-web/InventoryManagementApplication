package com.tcs.inventory.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "vendors")
public class Vendor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String vendorCode;
    private String name;
    private String contact;
    private String email;
    private String address;
//    @OneToMany(mappedBy = "vendor")
//    private List<PurchaseOrder> purchaseOrders;
//    
//    @OneToMany(mappedBy = "vendor")
//    private List<Invoice> invoices;
    
    @OneToOne
    @JoinColumn(name = "product_id", unique = true)
    private Product product;
    

    

}