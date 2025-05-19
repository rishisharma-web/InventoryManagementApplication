package com.tcs.inventory.dto;

import lombok.Data;

@Data
public class CustomerRegistrationDTO {
    private String customerCode;
    private String name;
    private String contact;
    private String email;
    private String address;

    private String username;
    private String password;
}
