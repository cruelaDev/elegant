package org.example.elegant.rating;

import org.example.elegant.common.repository.CommonRepository;
import org.example.elegant.rating.entity.Rating;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RatingRepository extends CommonRepository<Rating, UUID> {
}
