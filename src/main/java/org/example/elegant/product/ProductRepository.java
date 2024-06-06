package org.example.elegant.product;

import org.example.elegant.common.repository.CommonRepository;
import org.example.elegant.product.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends CommonRepository<Product, UUID> {
    Optional<Product> findByTitle(String productTitle);
}
