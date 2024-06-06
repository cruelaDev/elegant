package org.example.elegant.wishlist.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WishlistCreateDto {
    private UUID userId;
}

