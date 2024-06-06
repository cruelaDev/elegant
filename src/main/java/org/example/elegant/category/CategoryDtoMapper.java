package org.example.elegant.category;

import lombok.RequiredArgsConstructor;
import org.example.elegant.category.dto.CategoryCreateDto;
import org.example.elegant.category.dto.CategoryResponseDto;
import org.example.elegant.category.entity.Category;
import org.example.elegant.common.mapper.CommonMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryDtoMapper extends CommonMapper<Category, CategoryCreateDto, CategoryResponseDto, CategoryCreateDto> {

    private final ModelMapper mapper;
    @Override
    public Category toEntity(CategoryCreateDto categoryCreateDto) {
        return mapper.map(categoryCreateDto,Category.class);
    }

    @Override
    public CategoryResponseDto toResponseDto(Category category) {
        return mapper.map(category, CategoryResponseDto.class);
    }

    @Override
    public void toEntity(CategoryCreateDto categoryUpdateDto, Category category) {
        mapper.map(categoryUpdateDto,category);
    }
}
