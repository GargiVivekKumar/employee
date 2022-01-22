package com.example.employeedemo.dto;

import lombok.Data;

@Data
public class AddressDTO {
    private Long id;

    private Long empId;

    private String houseNumber;

    private String street;

    private String city;

    private int pinCode;
}
