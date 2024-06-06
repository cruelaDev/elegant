package org.example.elegant.product;

import jakarta.persistence.EntityNotFoundException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.elegant.category.CategoryRepository;
import org.example.elegant.category.entity.Category;
import org.example.elegant.common.service.CommonService;
import org.example.elegant.product.dto.ProductCreateDto;
import org.example.elegant.product.dto.ProductResponseDto;
import org.example.elegant.product.dto.ProductUpdateDto;
import org.example.elegant.product.entity.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Getter
public class ProductService extends CommonService<Product, UUID, ProductCreateDto, ProductResponseDto, ProductUpdateDto> {

    private final ProductDtoMapper mapper;
    private final ProductRepository repository;
    private final Class<Product> entityClass = Product.class;
    private final CategoryRepository categoryRepository;

    @Override
    protected ProductResponseDto internalCreate(ProductCreateDto productCreateDto) throws IOException {
        Product product = mapper.toEntity(productCreateDto);

        MultipartFile image = productCreateDto.getImage();
        String fileName = UUID.randomUUID() + image.getOriginalFilename();
        byte[] bytes = image.getBytes();
        String base64Img = Base64.getEncoder().encodeToString(bytes);
        product.setFileName(fileName);
        product.setContentType(image.getContentType());
        product.setImg(base64Img);

        Set<String> dtoCategoryNames = productCreateDto.getCategories();
        Set<Category> categories = categoryRepository.findAllByNameIn(dtoCategoryNames);

        if (dtoCategoryNames.size() != categories.size()) {
            Set<String> categoryNames = categories
                    .stream()
                    .map(Category::getName)
                    .collect(Collectors.toSet());

            dtoCategoryNames.removeAll(categoryNames);
            throw new EntityNotFoundException("Category with these names are not found.Categories: %s "
                    .formatted(dtoCategoryNames));
        }

        product.setCategories(categories);
        product.setId(UUID.randomUUID());
        Product saved = repository.save(product);
        return mapper.toResponseDto(saved);

    }

    @Override
    protected ProductResponseDto internalUpdate(UUID uuid, ProductUpdateDto productUpdateDto) {
        Product product = repository.findById(uuid).orElseThrow(
                () -> new EntityNotFoundException("Product with id: %s not fount".formatted(uuid)));
        mapper.toEntity(productUpdateDto, product);
        Product saved = repository.save(product);

        return mapper.toResponseDto(saved);
    }

    public ProductResponseDto getByTitle(String productTitle) {
        Product product = repository
                .findByTitle(productTitle)
                .orElseThrow(
                        () -> new EntityNotFoundException("Product with name: %s not found".formatted(productTitle)));
        return mapper.toResponseDto(product);
    }
}

