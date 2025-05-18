package com.tcs.inventory.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.tcs.inventory.dto.CustomerDTO;
import com.tcs.inventory.entity.Customer;
import com.tcs.inventory.repository.CustomerRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerDTO createCustomer(CustomerDTO dto) {
        Customer customer = new Customer();
        customer.setCustomerCode(generateCustomerCode());
        customer.setName(dto.getName());
        customer.setContact(dto.getContact());
        customer.setEmail(dto.getEmail());   
        customer.setAddress(dto.getAddress()); 
        customer = customerRepository.save(customer);
        return convertToDTO(customer);
    }

    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    
    
    private String generateCustomerCode() {
        // Simple UUID-based code. You can replace this with your own logic (e.g., incremental ID, prefix, etc.)
        return "CUST-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    // 🔧 Helper Method 2: Convert Entity to DTO
    private CustomerDTO convertToDTO(Customer customer) {
        CustomerDTO dto = new CustomerDTO();
        dto.setCustomerCode(customer.getCustomerCode());
        dto.setName(customer.getName());
        dto.setContact(customer.getContact());
        dto.setEmail(customer.getEmail());      
        dto.setAddress(customer.getAddress()); 
        return dto;
    }
}
