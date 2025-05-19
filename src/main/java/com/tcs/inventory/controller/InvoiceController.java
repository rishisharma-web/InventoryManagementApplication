package com.tcs.inventory.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.inventory.dto.InvoiceDTO;
import com.tcs.inventory.dto.SalesInvoiceRequest;
import com.tcs.inventory.entity.Invoice;
import com.tcs.inventory.enums.InvoiceStatus;
import com.tcs.inventory.service.InvoiceService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

//@RestController
//@RequestMapping("/api/invoices")
//@RequiredArgsConstructor
//public class InvoiceController {
//    private final InvoiceService invoiceService;
//
//    // For admin (Purchase Invoice)
//    @PostMapping("/purchase/{poNumber}")
//    public ResponseEntity<InvoiceDTO> createPurchaseInvoice(@PathVariable String poNumber) {
//        InvoiceDTO invoiceDTO = invoiceService.createPurchaseInvoiceFromPO(poNumber);
//        return ResponseEntity.ok(invoiceDTO);
//    }
//
//    // For customer (Sales Invoice)
//    @PostMapping("/sales")
//    public ResponseEntity<InvoiceDTO> createSalesInvoice(@Valid @RequestBody SalesInvoiceRequest request) {
//        InvoiceDTO invoiceDTO = invoiceService.createSalesInvoice(request);
//        return ResponseEntity.ok(invoiceDTO);
//    }
//    
//    @GetMapping("/{invoiceNumber}")
//    public ResponseEntity<InvoiceDTO> getInvoiceByNumber(@PathVariable String invoiceNumber) {
//        Invoice invoice = invoiceService.getInvoiceByNumber(invoiceNumber);
//        return ResponseEntity.ok(invoiceService.convertToDTO(invoice));
//    }
//
//}

//@RestController
//@RequestMapping("/admin/invoices") // <--- This groups admin endpoints under one base path
//@RequiredArgsConstructor
//public class InvoiceController {
//    
//    private final InvoiceService invoiceService;
//
//    @PostMapping("/purchase/{poNumber}")
//    public ResponseEntity<InvoiceDTO> createPurchaseInvoice(@PathVariable String poNumber) {
//        System.out.println("✅ Received request to create invoice for PO: " + poNumber);
//        return ResponseEntity.ok(invoiceService.createPurchaseInvoiceFromPO(poNumber));
//    }
//
//    @GetMapping("/{invoiceNumber}")
//    public ResponseEntity<InvoiceDTO> getInvoiceByNumber(@PathVariable String invoiceNumber) {
//        return ResponseEntity.ok(invoiceService.convertToDTO(invoiceService.getInvoiceByNumber(invoiceNumber)));
//    }
//}



@RestController
@RequiredArgsConstructor
public class InvoiceController {
    private final InvoiceService invoiceService;

    @PostMapping("/admin/invoices/purchase/{poNumber}")
    public ResponseEntity<InvoiceDTO> createPurchaseInvoice(@PathVariable String poNumber) {
    	 System.out.println("Received request to create invoice for PO: " + poNumber);
        return ResponseEntity.ok(invoiceService.createPurchaseInvoiceFromPO(poNumber));
    }

    @PostMapping("/customer/invoices/sales")
    public ResponseEntity<InvoiceDTO> createSalesInvoice(@Valid @RequestBody SalesInvoiceRequest request) {
        return ResponseEntity.ok(invoiceService.createSalesInvoice(request));
    }

    @GetMapping("/admin/invoices/{invoiceNumber}")
    public ResponseEntity<InvoiceDTO> getInvoiceByNumber(@PathVariable String invoiceNumber) {
        return ResponseEntity.ok(invoiceService.convertToDTO(invoiceService.getInvoiceByNumber(invoiceNumber)));
    }
}
