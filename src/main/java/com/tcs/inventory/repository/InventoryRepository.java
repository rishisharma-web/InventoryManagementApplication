//package com.tcs.inventory.repository;
//
//import java.util.Optional;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//import com.tcs.inventory.entity.Inventory;
//import com.tcs.inventory.entity.Product;
//
//@Repository
//public interface InventoryRepository extends JpaRepository<Inventory, Long> {
//	Optional<Inventory> findByProduct_ProductCode(String productCode);
//}
package com.tcs.inventory.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tcs.inventory.entity.Inventory;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
//	Optional<Inventory> findByProduct_ProductCode(String productCode);
}
