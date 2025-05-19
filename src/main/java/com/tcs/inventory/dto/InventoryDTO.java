//package com.tcs.inventory.dto;
//
//import lombok.Data;
//
//@Data
//public class InventoryDTO {
//    private String productCode;
//    private String productName;
//    private Integer quantity;
//}


//package com.tcs.inventory.dto;
//
//import lombok.Data;
//import java.util.List;
//
//@Data
//public class InventoryDTO {
////	private String productCode;
////	private String productName;
//    private Long id;
//    private List<ProductDTO> products;
//}


//package com.tcs.inventory.dto;
//
//import lombok.Data;
//import java.util.List;
//
//@Data
//public class InventoryDTO {
//    private List<ProductDTO> products;
//}

package com.tcs.inventory.dto;
import lombok.Data;

@Data
public class InventoryDTO {
    private String productCode;
    private Integer quantity;
}
