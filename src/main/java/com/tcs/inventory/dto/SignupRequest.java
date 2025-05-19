
package com.tcs.inventory.dto;

import lombok.Data;

@Data
public class SignupRequest {
    private String username;
    private String password;
    private String name;
    private String contact;
    private String email;
    private String address;
}
