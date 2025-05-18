package com.tcs.inventory.dto;

import lombok.Data;

@Data
public class CustomerDTO {
    private String customerCode;
    private String name;
    private String contact;
    private String email;
    private String address;
    
}