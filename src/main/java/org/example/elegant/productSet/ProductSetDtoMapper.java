package org.example.elegant.productSet;

import lombok.RequiredArgsConstructor;
import org.example.elegant.common.mapper.CommonMapper;
import org.example.elegant.productSet.dto.ProductSetCreateDto;
import org.example.elegant.productSet.dto.ProductSetResponseDto;
import org.example.elegant.productSet.dto.ProductSetUpdateDto;
import org.example.elegant.productSet.entity.ProductSet;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductSetDtoMapper extends CommonMapper<ProductSet, ProductSetCreateDto, ProductSetResponseDto, ProductSetUpdateDto> {

    private final ModelMapper mapper;

    @Override
    public ProductSet toEntity(ProductSetCreateDto productSetCreateDto) {
        return mapper.map(productSetCreateDto, ProductSet.class);
    }

    @Override
    public ProductSetResponseDto toResponseDto(ProductSet productSet) {
        return mapper.map(productSet, ProductSetResponseDto.class);
    }

    @Override
    public void toEntity(ProductSetUpdateDto productSetUpdateDto, ProductSet productSet) {
        mapper.map(productSetUpdateDto,productSet);
    }
}
