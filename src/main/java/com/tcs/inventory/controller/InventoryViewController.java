package com.tcs.inventory.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.inventory.dto.ProductInventoryViewDTO;
import com.tcs.inventory.service.InventoryViewService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryViewController {

    private final InventoryViewService inventoryViewService;

    @GetMapping
    public List<ProductInventoryViewDTO> getInventory() {
        return inventoryViewService.getInventory();
    }
}
