package com.tcs.inventory.dto;

import lombok.Data;

@Data
public class VendorDTO {
    private String vendorCode;
    private String name;
    private String contact;
    private String email;
    private String address;
    private String productCode;

}