////package com.tcs.inventory.service;
////
////import java.util.List;
////import java.util.stream.Collectors;
////
////import org.springframework.stereotype.Service;
////
////import com.tcs.inventory.dto.InventoryDTO;
////import com.tcs.inventory.entity.Inventory;
////import com.tcs.inventory.entity.Product;
////import com.tcs.inventory.exceptions.InsufficientInventoryException;
////import com.tcs.inventory.exceptions.ProductNotFoundException;
////import com.tcs.inventory.repository.InventoryRepository;
////
////import jakarta.transaction.Transactional;
////import lombok.RequiredArgsConstructor;
////
////@Service
////@Transactional
////@RequiredArgsConstructor
////public class InventoryService {
////
////    private final InventoryRepository inventoryRepository;
////
////    public boolean isAvailable(String productCode, int requestedQuantity) {
////        Inventory inventory = inventoryRepository.findByProduct_ProductCode(productCode)
////                .orElseThrow(() -> new ProductNotFoundException(productCode));
////        return inventory.getQuantity() >= requestedQuantity;
////    }
////
////    public void updateInventory(String productCode, int quantityChange) {
////        Inventory inventory = inventoryRepository.findByProduct_ProductCode(productCode)
////                .orElseThrow(() -> new ProductNotFoundException(productCode));
////
////        int newQuantity = inventory.getQuantity() + quantityChange;
////        if (newQuantity < 0) {
////            throw new InsufficientInventoryException(
////                "Cannot reduce inventory below 0 for product: " + productCode);
////        }
////
////        inventory.setQuantity(newQuantity);
////        inventoryRepository.save(inventory);
////    }
////
////    public InventoryDTO getInventoryByProductCode(String productCode) {
////        Inventory inventory = inventoryRepository.findByProduct_ProductCode(productCode)
////                .orElseThrow(() -> new ProductNotFoundException(productCode));
////        return convertToDTO(inventory);
////    }
////
////    public List<InventoryDTO> getAllInventory() {
////        return inventoryRepository.findAll()
////                .stream()
////                .map(this::convertToDTO)
////                .collect(Collectors.toList());
////    }
////
////    private InventoryDTO convertToDTO(Inventory inventory) {
////        InventoryDTO dto = new InventoryDTO();
////        Product product = inventory.getProduct();
////        dto.setProductCode(product.getProductCode());
////        dto.setProductName(product.getName());
////        dto.setQuantity(inventory.getQuantity());
////        return dto;
////    }
////}
//
//
////package com.tcs.inventory.service;
////
////import com.tcs.inventory.dto.ProductDTO;
////import com.tcs.inventory.entity.Inventory;
////import com.tcs.inventory.entity.Product;
////import com.tcs.inventory.exceptions.InsufficientInventoryException;
////import com.tcs.inventory.exceptions.ProductNotFoundException;
////import com.tcs.inventory.repository.InventoryRepository;
////import com.tcs.inventory.repository.ProductRepository;
////
////import jakarta.transaction.Transactional;
////import lombok.RequiredArgsConstructor;
////import org.springframework.stereotype.Service;
////
////import java.util.Optional;
////
////@Service
////@RequiredArgsConstructor
////public class InventoryService {
////
////    private final InventoryRepository inventoryRepository;
////    private final ProductRepository productRepository;
////
////    public boolean isAvailable(String productCode, int requestedQuantity) {
////        Product product = productRepository.findByProductCode(productCode)
////                .orElseThrow(() -> new ProductNotFoundException(productCode));
////        return product.getQuantity() >= requestedQuantity;
////    }
////
////    @Transactional
////    public void updateInventory(String productCode, int quantityChange) {
////        Product product = productRepository.findByProductCode(productCode)
////                .orElseThrow(() -> new ProductNotFoundException(productCode));
////           System.out.println("checkpoint 9");
////        int newQuantity = product.getQuantity() + quantityChange;
////        System.out.println("checkpoint 10");
////        if (newQuantity < 0) {
////            throw new InsufficientInventoryException("Cannot reduce inventory below 0 for product: " + productCode);
////        }
////
////        product.setQuantity(newQuantity);
////        productRepository.save(product);
////    }
////
////    public Inventory getInventoryWithProducts(Long inventoryId) {
////        return inventoryRepository.findById(inventoryId)
////                .orElseThrow(() -> new RuntimeException("Inventory not found with id: " + inventoryId));
////    }
////}
//
//package com.tcs.inventory.service;
//
//import com.tcs.inventory.entity.Inventory;
//import com.tcs.inventory.entity.Product;
//import com.tcs.inventory.exceptions.InsufficientInventoryException;
//import com.tcs.inventory.exceptions.ProductNotFoundException;
//import com.tcs.inventory.repository.InventoryRepository;
//import com.tcs.inventory.repository.ProductRepository;
//
//import jakarta.transaction.Transactional;
//import lombok.RequiredArgsConstructor;
//
//import java.util.List;
//
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class InventoryService {
//
//    private final InventoryRepository inventoryRepository;
//    private final ProductRepository productRepository;
//
//    public boolean isAvailable(String productCode, int requestedQuantity) {
//        Product product = productRepository.findByProductCode(productCode)
//                .orElseThrow(() -> new ProductNotFoundException(productCode));
//        Integer currentQuantity = product.getQuantity();
//        return currentQuantity != null && currentQuantity >= requestedQuantity;
//    }
//
////    @Transactional
////    public void updateInventory(String productCode, int quantityChange) {
////        Product product = productRepository.findByProductCode(productCode)
////                .orElseThrow(() -> new ProductNotFoundException(productCode));
////
////        int currentQuantity = product.getQuantity() != null ? product.getQuantity() : 0;
////        int newQuantity = currentQuantity + quantityChange;
////
////        if (newQuantity < 0) {
////            throw new InsufficientInventoryException("Cannot reduce inventory below 0 for product: " + productCode);
////        }
////
////        product.setQuantity(newQuantity);
////        productRepository.save(product);
////    }
//    
//    @Transactional
//    public void updateInventory(String productCode, int quantityChange) {
//        Product product = productRepository.findByProductCode(productCode)
//                .orElseThrow(() -> new ProductNotFoundException(productCode));
//
//        int newQuantity = (product.getQuantity() == null ? 0 : product.getQuantity()) + quantityChange;
//        if (newQuantity < 0) {
//            throw new InsufficientInventoryException("Cannot reduce inventory below 0 for product: " + productCode);
//        }
//
//        product.setQuantity(newQuantity);
//
//        // Ensure product is linked to Inventory ID 1
//        if (product.getInventory() == null) {
//            Inventory inventory = inventoryRepository.findById(1L)
//                    .orElseThrow(() -> new RuntimeException("Inventory with ID 1 not found"));
//            product.setInventory(inventory);
//        }
//
//        productRepository.save(product);
//    }
//
//
//    public Inventory getInventoryWithProducts() {
//        return inventoryRepository.findById(1L)
//                .orElseThrow(() -> new RuntimeException("Inventory not initialized"));
//    }
//    
//    
//    @Transactional
//    public void backfillInventoryForExistingProducts() {
//        Inventory inventory = inventoryRepository.findById(1L)
//                .orElseThrow(() -> new RuntimeException("Inventory with ID 1 not found"));
//
//        List<Product> products = productRepository.findAll();
//        for (Product product : products) {
//            if (product.getInventory() == null && product.getQuantity() != null && product.getQuantity() > 0) {
//                product.setInventory(inventory);
//                productRepository.save(product);
//            }
//        }
//    }
//
//}
//

package com.tcs.inventory.service;
import java.util.List;

import org.springframework.stereotype.Service;

import com.tcs.inventory.dto.ProductInventoryViewDTO;
import com.tcs.inventory.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventoryService {

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
