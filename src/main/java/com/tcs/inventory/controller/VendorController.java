package com.tcs.inventory.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.inventory.dto.VendorDTO;
import com.tcs.inventory.service.VendorService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/vendors")
@RequiredArgsConstructor
public class VendorController {
    private final VendorService vendorService;

    @PostMapping
    public ResponseEntity<VendorDTO> createVendor(@Valid @RequestBody VendorDTO dto) {
        return ResponseEntity.ok(vendorService.createVendor(dto));
    }

    @GetMapping
    public ResponseEntity<List<VendorDTO>> getAllVendors() {
        return ResponseEntity.ok(vendorService.getAllVendors());
    }
}