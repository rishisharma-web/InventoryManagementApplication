////package com.tcs.inventory.entity;
////
////import jakarta.persistence.*;
////import lombok.Data;
////import java.util.List;
////
////@Entity
////@Table(name = "inventory")
////@Data
////public class Inventory {
////    @Id
////    @GeneratedValue(strategy = GenerationType.IDENTITY)
////    private Long id;
////
////    // One inventory has many products
////    @OneToMany(mappedBy = "inventory", cascade = CascadeType.ALL, orphanRemoval = true)
////    private List<Product> products;
////   
////}
//package com.tcs.inventory.entity;
//
//import jakarta.persistence.*;
//import lombok.Data;
//import java.util.List;
//
//import com.fasterxml.jackson.annotation.JsonManagedReference;
//
//@Entity
//@Table(name = "inventory")
//@Data
//public class Inventory {
//
//    @Id
//    private Long id = 1L; // Fixed ID to always refer to the single inventory
//
//    @OneToMany(mappedBy = "inventory", cascade = CascadeType.ALL, orphanRemoval = true)
//    @JsonManagedReference
//    private List<Product> products;
//
//}
//
