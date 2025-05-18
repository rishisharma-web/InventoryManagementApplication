package com.tcs.inventory.exceptions;

public class InsufficientInventoryException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public InsufficientInventoryException(String message) {
        super(message);
    }

    public InsufficientInventoryException(String productCode, int requested, int available) {
        super(String.format("Insufficient inventory for product %s. Requested: %d, Available: %d", 
                          productCode, requested, available));
    }

    public InsufficientInventoryException(String message, Throwable cause) {
        super(message, cause);
    }
}