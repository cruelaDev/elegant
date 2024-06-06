package org.example.elegant.cart.dto;

import lombok.*;
import org.example.elegant.productSet.dto.ProductSetResponseDto;
import org.example.elegant.user.dto.UserResponseDtoForCartAndOrder;

import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartResponseDto{
    private UUID id;
    private UserResponseDtoForCartAndOrder user;
    private Set<ProductSetResponseDto> products;
}
