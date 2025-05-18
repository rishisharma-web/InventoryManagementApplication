package com.tcs.inventory.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class InvoiceItemDTO {
    private String productCode;
    private Integer quantity;
    private BigDecimal unitPrice;
}