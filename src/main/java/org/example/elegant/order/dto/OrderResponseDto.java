package org.example.elegant.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.elegant.address.dto.AddressResponseDto;
import org.example.elegant.productSet.dto.ProductSetResponseDto;
import org.example.elegant.user.dto.UserResponseDtoForCartAndOrder;

import java.util.Set;
import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderResponseDto{
    private UUID id;
    private AddressResponseDto address;
    private UserResponseDtoForCartAndOrder user;
    private Set<ProductSetResponseDto> products;
}
