package org.example.elegant.rating.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RatingCreateDto{
    private UUID userId;
    private UUID productId;
    private int stars;
}
