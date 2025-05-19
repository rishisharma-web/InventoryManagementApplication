package com.tcs.inventory.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.inventory.dto.PurchaseOrderDTO;
import com.tcs.inventory.enums.POStatus;
import com.tcs.inventory.service.PurchaseOrderService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

//@RestController
//@RequestMapping("/api/purchase-orders")
//@RequiredArgsConstructor
//public class PurchaseOrderController {
//    private final PurchaseOrderService purchaseOrderService;
//
//    @PostMapping
//    public ResponseEntity<PurchaseOrderDTO> createPurchaseOrder(
//            @Valid @RequestBody PurchaseOrderDTO dto) {
//        return ResponseEntity.ok(purchaseOrderService.createPurchaseOrder(dto));
//    }
//
//    @PutMapping("/{poNumber}/status")
//    public ResponseEntity<Void> updateStatus(
//            @PathVariable String poNumber,
//            @RequestParam POStatus status) {
//        purchaseOrderService.updateStatus(poNumber, status);
//        return ResponseEntity.ok().build();
//    }
//    
//    @GetMapping("/{poNumber}")
//    public ResponseEntity<PurchaseOrderDTO> getPurchaseOrderByPoNumber(@PathVariable String poNumber) {
//        return ResponseEntity.ok(purchaseOrderService.getPurchaseOrderByPoNumber(poNumber));
//    }
//
//}


@RestController
@RequiredArgsConstructor
public class PurchaseOrderController {

    private final PurchaseOrderService purchaseOrderService;

    @PostMapping("/admin/purchase-orders")
    public ResponseEntity<PurchaseOrderDTO> createPurchaseOrder(@Valid @RequestBody PurchaseOrderDTO dto) {
        return ResponseEntity.ok(purchaseOrderService.createPurchaseOrder(dto));
    }

    @PutMapping("/admin/purchase-orders/{poNumber}/status")
    public ResponseEntity<Void> updateStatus(@PathVariable String poNumber, @RequestParam POStatus status) {
        purchaseOrderService.updateStatus(poNumber, status);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/admin/purchase-orders/{poNumber}")
    public ResponseEntity<PurchaseOrderDTO> getPurchaseOrderByPoNumber(@PathVariable String poNumber) {
        return ResponseEntity.ok(purchaseOrderService.getPurchaseOrderByPoNumber(poNumber));
    }
}

