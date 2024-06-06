package org.example.elegant.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderCreateDto{
    private UUID userId;
    private String city;
    private String street;
    private String country;
    private int zipcode;
    private String state;
    private String phoneNumber;
}
