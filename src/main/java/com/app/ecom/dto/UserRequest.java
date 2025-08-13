package com.app.ecom.dto;

import com.app.ecom.model.UserRole;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRequest {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private AddressDTO address;
}
