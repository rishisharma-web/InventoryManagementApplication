package com.tcs.inventory.exceptions;

public class CustomerNotFoundException extends ResourceNotFoundException {
    public CustomerNotFoundException(String customerCode) {
        super("Customer not found: " + customerCode);
    }
}