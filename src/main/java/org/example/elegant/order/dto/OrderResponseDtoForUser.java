package org.example.elegant.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.elegant.address.dto.AddressResponseDto;
import org.example.elegant.productSet.dto.ProductSetResponseDto;

import java.util.Set;
import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDtoForUser {
    private UUID id;
    private AddressResponseDto address;
    private Set<ProductSetResponseDto> products;
}
