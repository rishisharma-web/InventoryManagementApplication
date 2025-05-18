package com.tcs.inventory.exceptions;

public class VendorNotFoundException extends ResourceNotFoundException {
    public VendorNotFoundException(String vendorCode) {
        super("Vendor not found: " + vendorCode);
    }
}