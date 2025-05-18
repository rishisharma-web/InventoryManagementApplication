package com.tcs.inventory.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tcs.inventory.entity.Customer;
import com.tcs.inventory.entity.Invoice;
import com.tcs.inventory.entity.Vendor;
import com.tcs.inventory.enums.InvoiceType;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    Optional<Invoice> findByInvoiceNumber(String invoiceNumber);
    List<Invoice> findByVendorAndType(Vendor vendor, InvoiceType type);
    List<Invoice> findByCustomerAndType(Customer customer, InvoiceType type);
}