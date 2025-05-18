package com.tcs.inventory.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.tcs.inventory.dto.CustomerDTO;
import com.tcs.inventory.dto.VendorDTO;
import com.tcs.inventory.entity.Customer;
import com.tcs.inventory.entity.Product;
import com.tcs.inventory.entity.Vendor;
import com.tcs.inventory.repository.ProductRepository;
import com.tcs.inventory.repository.VendorRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class VendorService {
    private final VendorRepository vendorRepository;
    private final ProductRepository productRepository;

    public VendorDTO createVendor(VendorDTO dto) {
        Vendor vendor = new Vendor();
        vendor.setVendorCode(generateVendorCode());
        vendor.setName(dto.getName());
        vendor.setContact(dto.getContact());
        vendor.setEmail(dto.getEmail());
        vendor.setAddress(dto.getAddress());
        
        
        // Find product by code
        if (dto.getProductCode() != null) {
            Product product = productRepository.findByProductCode(dto.getProductCode())
                    .orElseThrow(() -> new IllegalArgumentException("Product with code " + dto.getProductCode() + " not found"));
            vendor.setProduct(product);
        }
        
        vendor = vendorRepository.save(vendor);
        return convertToDTO(vendor);
    }

    public List<VendorDTO> getAllVendors() {
        return vendorRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    private String generateVendorCode() {
        // Simple UUID-based code. You can replace this with your own logic (e.g., incremental ID, prefix, etc.)
        return "VEND-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
    private VendorDTO convertToDTO(Vendor vendor) {
        VendorDTO dto = new VendorDTO();
        dto.setVendorCode(vendor.getVendorCode());
        dto.setName(vendor.getName());
        dto.setContact(vendor.getContact());
        dto.setEmail(vendor.getEmail());      
        dto.setAddress(vendor.getAddress()); 
        if (vendor.getProduct() != null) {
            dto.setProductCode(vendor.getProduct().getProductCode());
        }
        return dto;
    }
}
