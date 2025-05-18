//package com.tcs.inventory.service;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//import org.springframework.stereotype.Service;
//
//import com.tcs.inventory.dto.InventoryDTO;
//import com.tcs.inventory.entity.Inventory;
//import com.tcs.inventory.entity.Product;
//import com.tcs.inventory.exceptions.InsufficientInventoryException;
//import com.tcs.inventory.exceptions.ProductNotFoundException;
//import com.tcs.inventory.repository.InventoryRepository;
//
//import jakarta.transaction.Transactional;
//import lombok.RequiredArgsConstructor;
//
//@Service
//@Transactional
//@RequiredArgsConstructor
//public class InventoryService {
//
//    private final InventoryRepository inventoryRepository;
//
//    public boolean isAvailable(String productCode, int requestedQuantity) {
//        Inventory inventory = inventoryRepository.findByProduct_ProductCode(productCode)
//                .orElseThrow(() -> new ProductNotFoundException(productCode));
//        return inventory.getQuantity() >= requestedQuantity;
//    }
//
//    public void updateInventory(String productCode, int quantityChange) {
//        Inventory inventory = inventoryRepository.findByProduct_ProductCode(productCode)
//                .orElseThrow(() -> new ProductNotFoundException(productCode));
//
//        int newQuantity = inventory.getQuantity() + quantityChange;
//        if (newQuantity < 0) {
//            throw new InsufficientInventoryException(
//                "Cannot reduce inventory below 0 for product: " + productCode);
//        }
//
//        inventory.setQuantity(newQuantity);
//        inventoryRepository.save(inventory);
//    }
//
//    public InventoryDTO getInventoryByProductCode(String productCode) {
//        Inventory inventory = inventoryRepository.findByProduct_ProductCode(productCode)
//                .orElseThrow(() -> new ProductNotFoundException(productCode));
//        return convertToDTO(inventory);
//    }
//
//    public List<InventoryDTO> getAllInventory() {
//        return inventoryRepository.findAll()
//                .stream()
//                .map(this::convertToDTO)
//                .collect(Collectors.toList());
//    }
//
//    private InventoryDTO convertToDTO(Inventory inventory) {
//        InventoryDTO dto = new InventoryDTO();
//        Product product = inventory.getProduct();
//        dto.setProductCode(product.getProductCode());
//        dto.setProductName(product.getName());
//        dto.setQuantity(inventory.getQuantity());
//        return dto;
//    }
//}
package com.tcs.inventory.service;

import com.tcs.inventory.dto.ProductDTO;
import com.tcs.inventory.entity.Inventory;
import com.tcs.inventory.entity.Product;
import com.tcs.inventory.exceptions.InsufficientInventoryException;
import com.tcs.inventory.exceptions.ProductNotFoundException;
import com.tcs.inventory.repository.InventoryRepository;
import com.tcs.inventory.repository.ProductRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private final ProductRepository productRepository;

    public boolean isAvailable(String productCode, int requestedQuantity) {
        Product product = productRepository.findByProductCode(productCode)
                .orElseThrow(() -> new ProductNotFoundException(productCode));
        return product.getQuantity() >= requestedQuantity;
    }

    @Transactional
    public void updateInventory(String productCode, int quantityChange) {
        Product product = productRepository.findByProductCode(productCode)
                .orElseThrow(() -> new ProductNotFoundException(productCode));
           System.out.println("checkpoint 9");
        int newQuantity = product.getQuantity() + quantityChange;
        System.out.println("checkpoint 10");
        if (newQuantity < 0) {
            throw new InsufficientInventoryException("Cannot reduce inventory below 0 for product: " + productCode);
        }

        product.setQuantity(newQuantity);
        productRepository.save(product);
    }

    public Inventory getInventoryWithProducts(Long inventoryId) {
        return inventoryRepository.findById(inventoryId)
                .orElseThrow(() -> new RuntimeException("Inventory not found with id: " + inventoryId));
    }
}
