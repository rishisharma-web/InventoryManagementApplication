package com.tcs.inventory.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.inventory.dto.ProductDTO;
import com.tcs.inventory.service.ProductService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

//@RestController
//@RequestMapping("/api/products")
//@RequiredArgsConstructor
//public class ProductController {
//
//    private final ProductService productService;
//
//    @PostMapping
//    public ResponseEntity<ProductDTO> createProduct(@Valid @RequestBody ProductDTO dto) {
//        return ResponseEntity.ok(productService.createProduct(dto));
//    }
//
//    @GetMapping
//    public ResponseEntity<List<ProductDTO>> getAllProducts() {
//        return ResponseEntity.ok(productService.getAllProducts());
//    }
//
//    @GetMapping("/search")
//    public ResponseEntity<List<ProductDTO>> searchProducts(@RequestParam String name) {
//        return ResponseEntity.ok(productService.searchByName(name));
//    }
//}

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/admin/products")
    public ResponseEntity<ProductDTO> createProduct(@Valid @RequestBody ProductDTO dto) {
        return ResponseEntity.ok(productService.createProduct(dto));
    }

    @GetMapping("/admin/products")
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/admin/products/search")
    public ResponseEntity<List<ProductDTO>> searchProducts(@RequestParam String name) {
        return ResponseEntity.ok(productService.searchByName(name));
    }
}
