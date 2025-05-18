package com.tcs.inventory.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.tcs.inventory.enums.InvoiceStatus;
import com.tcs.inventory.enums.InvoiceType;

import lombok.Data;

@Data
public class InvoiceDTO {
	private Long id;
    private String invoiceNumber;
    private LocalDate invoiceDate;
    private LocalDate dueDate;
    private InvoiceType type;
    private String vendorCode;  // for purchase invoice
    private String customerCode;  // for sales invoice
    private List<InvoiceItemDTO> items;
    private InvoiceStatus status;
    private BigDecimal amount;
    private LocalDate date;
}