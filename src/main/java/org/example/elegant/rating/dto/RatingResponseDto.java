package org.example.elegant.rating.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RatingResponseDto {
    private UUID id;
    private String user;
    private String product;
    private int stars;
}
