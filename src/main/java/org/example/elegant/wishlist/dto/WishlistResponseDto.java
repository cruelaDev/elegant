package org.example.elegant.wishlist.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.elegant.product.dto.ProductResponseDto;
import org.example.elegant.user.dto.UserResponseDtoForCartAndOrder;

import java.util.Set;
import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WishlistResponseDto {
    private UUID id;
    private UserResponseDtoForCartAndOrder user;
    private Set<ProductResponseDto> products;
}
