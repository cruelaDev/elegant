package org.example.elegant.productSet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.elegant.product.dto.ProductResponseDto;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductSetResponseDto {
    private UUID id;
    private int quantity;
    private ProductResponseDto product;
}
