package com.tcs.inventory.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.inventory.dto.CustomerDTO;
import com.tcs.inventory.service.CustomerService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

//@RestController
//@RequestMapping("/api/customers")
//@RequiredArgsConstructor
//public class CustomerController {
//    private final CustomerService customerService;
//
//    @PostMapping
//    public ResponseEntity<CustomerDTO> createCustomer(@Valid @RequestBody CustomerDTO dto) {
//        return ResponseEntity.ok(customerService.createCustomer(dto));
//    }
//
//    @GetMapping
//    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
//        return ResponseEntity.ok(customerService.getAllCustomers());
//    }
//}



@RestController
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/public/customers")
    public ResponseEntity<CustomerDTO> createCustomer(@Valid @RequestBody CustomerDTO dto) {
        return ResponseEntity.ok(customerService.createCustomer(dto));
    }

    @GetMapping("/admin/customers")
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }
}
