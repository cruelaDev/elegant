package org.example.elegant.address.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressResponseDto{
    private UUID id;
    private String street;
    private String city;
    private String country;
    private int zipcode;
    private String state;
    private String phoneNumber;
}
