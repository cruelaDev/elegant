package org.example.elegant.wishlist.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.elegant.product.dto.ProductResponseDto;

import java.util.Set;
import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WishlistResponseDtoForUser {
    private UUID id;
    private Set<ProductResponseDto> products;
}
