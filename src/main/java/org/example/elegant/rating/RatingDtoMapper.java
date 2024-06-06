package org.example.elegant.rating;

import lombok.RequiredArgsConstructor;
import org.example.elegant.common.mapper.CommonMapper;
import org.example.elegant.rating.dto.RatingCreateDto;
import org.example.elegant.rating.dto.RatingResponseDto;
import org.example.elegant.rating.dto.RatingUpdateDto;
import org.example.elegant.rating.entity.Rating;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RatingDtoMapper extends CommonMapper<Rating, RatingCreateDto, RatingResponseDto, RatingUpdateDto> {
    private final ModelMapper mapper;

    @Override
    public Rating toEntity(RatingCreateDto ratingCreateDto) {
        return mapper.map(ratingCreateDto, Rating.class);
    }

    @Override
    public RatingResponseDto toResponseDto(Rating rating) {
        String userName = rating.getUser().getDisplayName();
        String productName = rating.getProduct().getTitle();
        RatingResponseDto responseDto = mapper.map(rating, RatingResponseDto.class);
        responseDto.setUser(userName);
        responseDto.setProduct(productName);
        return responseDto;
    }

    @Override
    public void toEntity(RatingUpdateDto ratingUpdateDto, Rating rating) {
        mapper.map(ratingUpdateDto, rating);
    }
}