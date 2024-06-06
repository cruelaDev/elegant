package org.example.elegant.category;

import jakarta.persistence.EntityNotFoundException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.elegant.category.dto.CategoryCreateDto;
import org.example.elegant.category.dto.CategoryResponseDto;
import org.example.elegant.category.entity.Category;
import org.example.elegant.common.service.CommonService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Getter
public class CategoryService extends CommonService<Category, String, CategoryCreateDto, CategoryResponseDto,CategoryCreateDto> {

    private final CategoryRepository repository;
    private final Class<Category> entityClass = Category.class;
    private final CategoryDtoMapper mapper;

    @Override
    protected CategoryResponseDto internalCreate(CategoryCreateDto categoryCreateDto) {
        Category entity = mapper.toEntity(categoryCreateDto);
        Category saved = repository.save(entity);
        return mapper.toResponseDto(saved);
    }

    @Override
    protected CategoryResponseDto internalUpdate(String id, CategoryCreateDto categoryUpdateDto) {
        Category category = repository
                .findById(id)
                .orElseThrow(
                        () -> new EntityNotFoundException("Category not found")
                );
        mapper.toEntity(categoryUpdateDto, category);
        Category saved = repository.save(category);
        return mapper.toResponseDto(saved);
    }
}
