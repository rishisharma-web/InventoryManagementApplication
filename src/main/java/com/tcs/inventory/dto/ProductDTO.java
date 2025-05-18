//package com.tcs.inventory.dto;
//
//import java.math.BigDecimal;
//
//import lombok.Data;
//
//@Data
//public class ProductDTO {
//    private String productCode;
//    private String name;
//    private BigDecimal basePrice;
//    private BigDecimal sellingPrice;
//}
package com.tcs.inventory.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ProductDTO {
    private String productCode;
    private String name;
    private BigDecimal basePrice;
    private BigDecimal sellingPrice;
    private Integer quantity;
}
