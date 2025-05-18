package com.tcs.inventory.exceptions;

public class ProductNotFoundException extends ResourceNotFoundException {
    public ProductNotFoundException(String productCode) {
        super("Product not found: " + productCode);
    }
}