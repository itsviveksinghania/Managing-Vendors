package com.example.Managing.Vendors.DTO;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class VendorDTO {

    private String vendorName;

    private String address;

    private String city;

    private String state;


    @Size(max = 6)
    @Pattern(regexp = "\\d{6}")
    private String pinCode;

    @Size(max = 10)
    @Pattern(regexp = "\\d{10}")
    private String phone;

    @Email
    private String email;
}
