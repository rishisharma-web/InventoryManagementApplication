//package com.tcs.inventory.controller;
//
//
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import com.tcs.inventory.dto.InventoryDTO;
//import com.tcs.inventory.entity.Inventory;
//import com.tcs.inventory.repository.InventoryRepository;
//
//import lombok.RequiredArgsConstructor;
//
//@RestController
//@RequestMapping("/api/inventory")
//@RequiredArgsConstructor
//public class InventoryController {
//    private final InventoryRepository inventoryRepository;
//
//    @GetMapping
//    public ResponseEntity<List<InventoryDTO>> getAllInventory() {
//        List<InventoryDTO> inventoryList = inventoryRepository.findAll()
//            .stream()
//            .map(inv -> {
//                InventoryDTO dto = new InventoryDTO();
//                dto.setProductCode(inv.getProduct().getProductCode());
//                dto.setProductName(inv.getProduct().getName());
//                dto.setQuantity(inv.getQuantity());
//                return dto;
//            })
//            .collect(Collectors.toList());
//
//        return ResponseEntity.ok(inventoryList);
//    }
//
//    @GetMapping("/{productCode}")
//    public ResponseEntity<InventoryDTO> getInventoryByProductCode(@PathVariable String productCode) {
//        Inventory inventory = inventoryRepository.findByProduct_ProductCode(productCode)
//            .orElseThrow(() -> new RuntimeException("Inventory not found for product code: " + productCode));
//
//        InventoryDTO dto = new InventoryDTO();
//        dto.setProductCode(inventory.getProduct().getProductCode());
//        dto.setProductName(inventory.getProduct().getName());
//        dto.setQuantity(inventory.getQuantity());
//
//        return ResponseEntity.ok(dto);
//    }
//}
package com.tcs.inventory.controller;

import com.tcs.inventory.entity.Inventory;
import com.tcs.inventory.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping("/{id}")
    public Inventory getInventory(@PathVariable Long id) {
        return inventoryService.getInventoryWithProducts(id);
    }

    @PostMapping("/update")
    public String updateInventory(@RequestParam String productCode, @RequestParam int quantityChange) {
        inventoryService.updateInventory(productCode, quantityChange);
        return "Inventory updated successfully";
    }

    @GetMapping("/check")
    public boolean isAvailable(@RequestParam String productCode, @RequestParam int quantity) {
        return inventoryService.isAvailable(productCode, quantity);
    }
}
