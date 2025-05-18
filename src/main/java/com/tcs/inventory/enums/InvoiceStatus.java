package com.tcs.inventory.enums;

public enum InvoiceStatus {
    PENDING,     // Just created
    PAID,        // Fully paid
    PARTIALLY_PAID,  // Partial payment received
    CANCELLED,   // Cancelled invoice
    OVERDUE      // Payment overdue
}