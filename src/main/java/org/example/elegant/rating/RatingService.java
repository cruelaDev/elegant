package org.example.elegant.rating;

import jakarta.persistence.EntityNotFoundException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.elegant.common.service.CommonService;
import org.example.elegant.product.ProductRepository;
import org.example.elegant.product.entity.Product;
import org.example.elegant.rating.dto.RatingCreateDto;
import org.example.elegant.rating.dto.RatingResponseDto;
import org.example.elegant.rating.dto.RatingUpdateDto;
import org.example.elegant.rating.entity.Rating;
import org.example.elegant.user.UserRepository;
import org.example.elegant.user.entity.User;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Getter
@RequiredArgsConstructor
public class RatingService extends CommonService<Rating, UUID, RatingCreateDto, RatingResponseDto, RatingUpdateDto> {
    private final RatingRepository repository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final Class<Rating> entityClass = Rating.class;
    private final RatingDtoMapper mapper;

    @Override
    protected RatingResponseDto internalCreate(RatingCreateDto ratingCreateDto) {
        Rating rating = mapper.toEntity(ratingCreateDto);

        UUID productId = ratingCreateDto.getProductId();
        UUID userId = ratingCreateDto.getUserId();

        Product product = productRepository.findById(productId).orElseThrow(() -> new EntityNotFoundException("Product not found"));
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));

        rating.setUser(user);
        rating.setProduct(product);
        rating.setId(UUID.randomUUID());
        product.getRatings().add(rating);

        productRepository.save(product);
        userRepository.save(user);
        Rating saved = repository.save(rating);
        return mapper.toResponseDto(saved);
    }

    @Override
    protected RatingResponseDto internalUpdate(UUID uuid, RatingUpdateDto ratingUpdateDto) {
        return null;
    }
}

