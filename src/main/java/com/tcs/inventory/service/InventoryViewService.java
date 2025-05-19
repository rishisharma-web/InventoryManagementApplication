package com.tcs.inventory.service;
import java.util.List;

import org.springframework.stereotype.Service;

import com.tcs.inventory.dto.ProductInventoryViewDTO;
import com.tcs.inventory.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventoryViewService {

    private final ProductRepository productRepository;

    public List<ProductInventoryViewDTO> getInventory() {
        return productRepository.findAll().stream()
                .filter(p -> p.getQuantity() != null && p.getQuantity() > 0)
                .map(p -> {
                    ProductInventoryViewDTO dto = new ProductInventoryViewDTO();
                    dto.setProductCode(p.getProductCode());
                    dto.setQuantity(p.getQuantity());
                    return dto;
                })
                .toList();
    }
}
