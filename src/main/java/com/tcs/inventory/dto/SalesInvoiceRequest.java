package com.tcs.inventory.dto;

import java.util.List;

import lombok.Data;

@Data
public class SalesInvoiceRequest {
    private String customerCode;
    private List<SalesItemRequest> items;
}