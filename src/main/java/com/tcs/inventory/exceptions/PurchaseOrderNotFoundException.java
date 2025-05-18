package com.tcs.inventory.exceptions;

public class PurchaseOrderNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public PurchaseOrderNotFoundException(String poNumber) {
        super("Purchase Order not found with number: " + poNumber);
    }

    public PurchaseOrderNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}