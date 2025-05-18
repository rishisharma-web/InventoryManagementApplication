//package com.tcs.inventory.repository;
//
//import java.util.Optional;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//import com.tcs.inventory.entity.Product;
//
//@Repository
//public interface ProductRepository extends JpaRepository<Product, Long> {
//    Optional<Product> findByProductCode(String productCode);
//}

package com.tcs.inventory.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tcs.inventory.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByProductCode(String code);
}
