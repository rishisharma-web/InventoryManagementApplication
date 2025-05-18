package com.tcs.inventory.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.tcs.inventory.dto.PurchaseOrderDTO;
import com.tcs.inventory.dto.PurchaseOrderItemDTO;
import com.tcs.inventory.entity.Product;
import com.tcs.inventory.entity.PurchaseOrder;
import com.tcs.inventory.entity.PurchaseOrderItem;
import com.tcs.inventory.entity.Vendor;
import com.tcs.inventory.enums.POStatus;
import com.tcs.inventory.repository.ProductRepository;
import com.tcs.inventory.repository.PurchaseOrderRepository;
import com.tcs.inventory.repository.VendorRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class PurchaseOrderService {
    private final PurchaseOrderRepository purchaseOrderRepository;
    private final VendorRepository vendorRepository;
    private final ProductRepository productRepository;

    public PurchaseOrderDTO createPurchaseOrder(PurchaseOrderDTO dto) {
        Vendor vendor = vendorRepository.findByVendorCode(dto.getVendorCode())
                .orElseThrow(() -> new RuntimeException("Vendor not found"));

        PurchaseOrder po = new PurchaseOrder();
        po.setPoNumber(generatePoNumber());
        po.setVendor(vendor);
        po.setOrderDate(LocalDate.now());
        po.setExpectedDeliveryDate(dto.getExpectedDeliveryDate());
        po.setStatus(POStatus.OPEN);  

        List<PurchaseOrderItem> items = dto.getItems().stream().map(itemDto -> {
            Product product = productRepository.findByProductCode(itemDto.getProductCode())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            PurchaseOrderItem item = new PurchaseOrderItem();
            item.setProduct(product);
            item.setQuantity(itemDto.getQuantity());
            item.setUnitPrice(itemDto.getUnitPrice());
            item.setPurchaseOrder(po);
            return item;
        }).collect(Collectors.toList());

        po.setItems(items);
        po.setTotalAmount(
            items.stream()
                 .map(i -> i.getUnitPrice().multiply(BigDecimal.valueOf(i.getQuantity())))
                 .reduce(BigDecimal.ZERO, BigDecimal::add)
        );

        purchaseOrderRepository.save(po);
        return dto;
    }

    public void updateStatus(String poNumber, POStatus status) {
        PurchaseOrder po = purchaseOrderRepository.findByPoNumber(poNumber)
                .orElseThrow(() -> new RuntimeException("PO not found"));
        po.setStatus(status); 
    }
    
    public PurchaseOrderDTO getPurchaseOrderByPoNumber(String poNumber) {
        PurchaseOrder po = purchaseOrderRepository.findByPoNumber(poNumber)
                .orElseThrow(() -> new RuntimeException("Purchase Order not found: " + poNumber));

        PurchaseOrderDTO dto = new PurchaseOrderDTO();
        dto.setVendorCode(po.getVendor().getVendorCode());
        dto.setExpectedDeliveryDate(po.getExpectedDeliveryDate());

        List<PurchaseOrderItemDTO> itemDTOs = po.getItems().stream().map(item -> {
            PurchaseOrderItemDTO itemDTO = new PurchaseOrderItemDTO();
            itemDTO.setProductCode(item.getProduct().getProductCode());
            itemDTO.setQuantity(item.getQuantity());
            itemDTO.setUnitPrice(item.getUnitPrice());
            return itemDTO;
        }).collect(Collectors.toList());

        dto.setItems(itemDTOs);

        return dto;
    }


    private String generatePoNumber() {
        return "PO-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}
