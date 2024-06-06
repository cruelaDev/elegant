package org.example.elegant.category.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.elegant.product.dto.ProductResponseDto;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponseDto {
    private String name;
    private List<ProductResponseDto> products;
}

