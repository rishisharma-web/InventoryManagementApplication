package com.tcs.inventory.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.tcs.inventory.dto.InvoiceDTO;
import com.tcs.inventory.dto.InvoiceItemDTO;
import com.tcs.inventory.dto.SalesInvoiceRequest;
import com.tcs.inventory.dto.SalesItemRequest;
import com.tcs.inventory.entity.Customer;
import com.tcs.inventory.entity.Invoice;
import com.tcs.inventory.entity.InvoiceItem;
import com.tcs.inventory.entity.Product;
import com.tcs.inventory.entity.PurchaseOrder;
import com.tcs.inventory.enums.InvoiceStatus;
import com.tcs.inventory.enums.InvoiceType;
import com.tcs.inventory.enums.POStatus;
import com.tcs.inventory.exceptions.CustomerNotFoundException;
import com.tcs.inventory.exceptions.InsufficientInventoryException;
import com.tcs.inventory.exceptions.InvalidOperationException;
import com.tcs.inventory.exceptions.InvoiceNotFoundException;
import com.tcs.inventory.exceptions.ProductNotFoundException;
import com.tcs.inventory.exceptions.PurchaseOrderNotFoundException;
import com.tcs.inventory.repository.CustomerRepository;
import com.tcs.inventory.repository.InvoiceRepository;
import com.tcs.inventory.repository.ProductRepository;
import com.tcs.inventory.repository.PurchaseOrderRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final PurchaseOrderRepository purchaseOrderRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final ProductService productService; // ✅ Use ProductService instead of InventoryService

    // For admin purchasing from vendor (Purchase Invoice from PO)
    public InvoiceDTO createPurchaseInvoiceFromPO(String poNumber) {
        PurchaseOrder po = purchaseOrderRepository.findByPoNumber(poNumber)
                .orElseThrow(() -> new PurchaseOrderNotFoundException("Purchase Order not found: " + poNumber));

        if (po.getStatus() != POStatus.OPEN) {
            throw new InvalidOperationException("Cannot create invoice for " + po.getStatus() + " purchase order");
        }

        Invoice invoice = Invoice.fromPurchaseOrder(po);
        invoice.setInvoiceNumber(generateInvoiceNumber());
        invoice.setType(InvoiceType.PURCHASE);
        invoice.setDueDate(invoice.getInvoiceDate().plusDays(30));

        // Update inventory (increase stock)
        for (InvoiceItem item : invoice.getItems()) {
            productService.updateQuantity(
                item.getProduct().getProductCode(),
                item.getQuantity()  // positive for purchase
            );
        }

        po.setStatus(POStatus.CLOSED);
        purchaseOrderRepository.save(po);
        invoiceRepository.save(invoice);
        return convertToDTO(invoice);
    }

    // For customer purchases (Sales Invoice)
    public InvoiceDTO createSalesInvoice(SalesInvoiceRequest request) {
        // Validate customer
        Customer customer = customerRepository.findByCustomerCode(request.getCustomerCode())
                .orElseThrow(() -> new CustomerNotFoundException(request.getCustomerCode()));

        Invoice invoice = new Invoice();
        invoice.setInvoiceNumber(generateInvoiceNumber());
        invoice.setInvoiceDate(LocalDate.now());
        invoice.setType(InvoiceType.SALES);
        invoice.setStatus(InvoiceStatus.PENDING);
        invoice.setCustomer(customer);
        invoice.setDueDate(LocalDate.now()); // For sales, usually immediate payment

        // Process each item in the request
        for (SalesItemRequest itemRequest : request.getItems()) {
            Product product = productRepository.findByProductCode(itemRequest.getProductCode())
                    .orElseThrow(() -> new ProductNotFoundException(itemRequest.getProductCode()));

            // ✅ Check inventory availability
            if (!productService.isAvailable(product.getProductCode(), itemRequest.getQuantity())) {
                throw new InsufficientInventoryException(
                    "Insufficient inventory for product: " + product.getProductCode());
            }

            InvoiceItem item = new InvoiceItem();
            item.setInvoice(invoice);
            item.setProduct(product);
            item.setQuantity(itemRequest.getQuantity());
            item.setUnitPrice(product.getSellingPrice()); // Use product's selling price
            invoice.addItem(item);

            // ✅ Update inventory (decrease stock)
            productService.updateQuantity(product.getProductCode(), -itemRequest.getQuantity());
        }

        invoiceRepository.save(invoice);
        return convertToDTO(invoice);
    }

    public Invoice getInvoiceByNumber(String invoiceNumber) {
        return invoiceRepository.findByInvoiceNumber(invoiceNumber)
                .orElseThrow(() -> new InvoiceNotFoundException("Invoice not found: " + invoiceNumber));
    }

    private String generateInvoiceNumber() {
        return "INV-" + System.currentTimeMillis();
    }

    public InvoiceDTO convertToDTO(Invoice invoice) {
        if (invoice == null) {
            return null;
        }

        InvoiceDTO dto = new InvoiceDTO();
        dto.setId(invoice.getId());
        dto.setInvoiceNumber(invoice.getInvoiceNumber());
        dto.setInvoiceDate(invoice.getInvoiceDate());
        dto.setDueDate(invoice.getDueDate());
        dto.setType(invoice.getType());
        dto.setStatus(invoice.getStatus());
        dto.setAmount(invoice.getTotalAmount());
        dto.setDate(invoice.getInvoiceDate());

        // Set vendorCode or customerCode based on type
        if (invoice.isPurchaseInvoice() && invoice.getVendor() != null) {
            dto.setVendorCode(invoice.getVendor().getVendorCode());
        } else if (invoice.isSalesInvoice() && invoice.getCustomer() != null) {
            dto.setCustomerCode(invoice.getCustomer().getCustomerCode());
        }

        // Convert items
        List<InvoiceItemDTO> itemDTOs = invoice.getItems().stream().map(item -> {
            InvoiceItemDTO itemDTO = new InvoiceItemDTO();
            itemDTO.setProductCode(item.getProduct().getProductCode());
            itemDTO.setQuantity(item.getQuantity());
            itemDTO.setUnitPrice(item.getUnitPrice());
            return itemDTO;
        }).toList();

        dto.setItems(itemDTOs);
        return dto;
    }
}
