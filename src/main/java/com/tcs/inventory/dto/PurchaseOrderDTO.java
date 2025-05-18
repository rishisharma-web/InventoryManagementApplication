package com.tcs.inventory.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.tcs.inventory.enums.POStatus;

import lombok.Data;

@Data
public class PurchaseOrderDTO {
    private String vendorCode;
    private LocalDate expectedDeliveryDate;
    private List<PurchaseOrderItemDTO> items;
    private String poNumber;
    private POStatus status;
    private BigDecimal totalAmount;

}