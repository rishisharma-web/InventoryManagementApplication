package com.tcs.inventory.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String customerCode;
    private String name;
    private String contact;
    private String email;
    private String address;
    
//    @OneToMany(mappedBy = "customer")
//    private List<Invoice> invoices;
    
    // getters, setters, constructors
}