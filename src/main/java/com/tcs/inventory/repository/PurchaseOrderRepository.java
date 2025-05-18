package com.tcs.inventory.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tcs.inventory.entity.PurchaseOrder;
import com.tcs.inventory.entity.Vendor;
import com.tcs.inventory.enums.POStatus;

@Repository
public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {
    Optional<PurchaseOrder> findByPoNumber(String poNumber);
    List<PurchaseOrder> findByVendorAndStatus(Vendor vendor, POStatus status);
}
