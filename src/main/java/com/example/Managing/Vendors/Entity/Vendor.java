package com.example.Managing.Vendors.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Entity
@Table
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vendor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vendor_id")
    private Long vendorId;

    @Column(name = "vendor_name", nullable = false)
    @NotNull
    @Size(min = 2, max = 50)
    private String vendorName;

    @Column(name = "vendor_code", nullable = false, unique = true)
    @Size(min = 2, max = 32)
    private String vendorCode;

    @Column(name = "address")
    @Size(max = 100)
    private String address;

    @Column(name = "city")
    @Size(max = 50)
    private String city;

    @Column(name = "state")
    @Size(max = 50)
    private String state;

    @Column(name = "pinCode")
    @Size(max = 6)
    @Pattern(regexp = "\\d{6}")
    private String pinCode;

    @Column(name = "phone")
    @Size(max = 10)
    @Pattern(regexp = "\\d{10}")
    private String phone;

    @Column(name = "email", nullable = false)
    @NotNull
    @Email
    private String email;


}
