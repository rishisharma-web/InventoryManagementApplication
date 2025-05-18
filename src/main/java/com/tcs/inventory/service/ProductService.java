package com.tcs.inventory.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.tcs.inventory.dto.ProductDTO;
import com.tcs.inventory.entity.Product;
import com.tcs.inventory.repository.ProductRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public ProductDTO createProduct(ProductDTO dto) {
        Product product = new Product();
        product.setProductCode(generateProductCode(dto.getName()));
        product.setName(dto.getName());
        product.setBasePrice(dto.getBasePrice());
        product.setSellingPrice(dto.getSellingPrice());
        product.setQuantity(dto.getQuantity());
        product = productRepository.save(product);
        return convertToDTO(product);
    }

    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<ProductDTO> searchByName(String name) {
        return productRepository.findAll().stream()
                .filter(p -> p.getName().toLowerCase().contains(name.toLowerCase()))
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private ProductDTO convertToDTO(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setProductCode(product.getProductCode());
        dto.setName(product.getName());
        dto.setBasePrice(product.getBasePrice());
        dto.setSellingPrice(product.getSellingPrice());
        dto.setQuantity(0);
        return dto;
    }

    private String generateProductCode(String name) {
        return (name.substring(0, Math.min(3, name.length())).toUpperCase()) + "-" + System.currentTimeMillis();
    }
}
