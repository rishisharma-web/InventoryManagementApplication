package com.tcs.inventory.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.tcs.inventory.enums.InvoiceStatus;
import com.tcs.inventory.enums.InvoiceType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "invoices")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String invoiceNumber;
    private LocalDate invoiceDate;
    
    @Enumerated(EnumType.STRING)
    private InvoiceType type; // PURCHASE, SALES
    
    @ManyToOne
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;  // For purchase invoices
    
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;  // For sales invoices
    
    @ManyToOne
    @JoinColumn(name = "purchase_order_id")
    private PurchaseOrder purchaseOrder;  // Only for purchase invoices
    
    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL)
    private List<InvoiceItem> items = new ArrayList<>();
    
    private BigDecimal totalAmount = BigDecimal.ZERO;
    private LocalDate dueDate;
    
    @Enumerated(EnumType.STRING)
    private InvoiceStatus status = InvoiceStatus.PENDING;

    // Method to add item and update total
    public void addItem(InvoiceItem item) {
        items.add(item);
        item.setInvoice(this);
        calculateTotal();
    }

    // Method to remove item and update total
    public void removeItem(InvoiceItem item) {
        items.remove(item);
        item.setInvoice(null);
        calculateTotal();
    }

    // Method to calculate total amount
    private void calculateTotal() {
        this.totalAmount = items.stream()
                .map(item -> item.getUnitPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    // Factory method to create Invoice from PurchaseOrder
    public static Invoice fromPurchaseOrder(PurchaseOrder po) {
        Invoice invoice = new Invoice();
        invoice.setPurchaseOrder(po);
        invoice.setVendor(po.getVendor());
        invoice.setType(InvoiceType.PURCHASE);
        invoice.setInvoiceDate(LocalDate.now());
        invoice.setStatus(InvoiceStatus.PENDING);

        // Convert PO items to Invoice items
        for (PurchaseOrderItem poItem : po.getItems()) {
            InvoiceItem invoiceItem = new InvoiceItem();
            invoiceItem.setInvoice(invoice);
            invoiceItem.setProduct(poItem.getProduct());
            invoiceItem.setQuantity(poItem.getQuantity());
            invoiceItem.setUnitPrice(poItem.getUnitPrice());
            invoice.addItem(invoiceItem);
        }

        return invoice;
    }

    // Factory method to create Sales Invoice
    public static Invoice createSalesInvoice(Customer customer) {
        Invoice invoice = new Invoice();
        invoice.setCustomer(customer);
        invoice.setType(InvoiceType.SALES);
        invoice.setInvoiceDate(LocalDate.now());
        invoice.setDueDate(LocalDate.now()); // For sales, usually immediate payment
        invoice.setStatus(InvoiceStatus.PENDING);
        return invoice;
    }

    // Validate invoice based on type
    public void validate() {
        if (type == InvoiceType.PURCHASE && vendor == null) {
            throw new IllegalStateException("Purchase invoice must have a vendor");
        }
        if (type == InvoiceType.SALES && customer == null) {
            throw new IllegalStateException("Sales invoice must have a customer");
        }
        if (items.isEmpty()) {
            throw new IllegalStateException("Invoice must have at least one item");
        }
    }

    // Check if invoice is a purchase invoice
    public boolean isPurchaseInvoice() {
        return type == InvoiceType.PURCHASE;
    }

    // Check if invoice is a sales invoice
    public boolean isSalesInvoice() {
        return type == InvoiceType.SALES;
    }

    // Get the party name (vendor or customer) based on invoice type
    public String getPartyName() {
        if (isPurchaseInvoice()) {
            return vendor != null ? vendor.getName() : null;
        } else {
            return customer != null ? customer.getName() : null;
        }
    }

    // Get number of items in invoice
    public int getItemCount() {
        return items.size();
    }

    // Clear all items
    public void clearItems() {
        items.clear();
        totalAmount = BigDecimal.ZERO;
    }

    // Check if invoice can be modified (based on status)
    public boolean isModifiable() {
        return status == InvoiceStatus.PENDING;
    }
}