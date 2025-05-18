package com.tcs.inventory.dto;

import lombok.Data;

@Data
public class SalesItemRequest {
    private String productCode;
    private Integer quantity;
}