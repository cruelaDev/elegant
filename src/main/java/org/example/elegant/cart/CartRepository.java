package org.example.elegant.cart;

import org.example.elegant.cart.entity.Cart;
import org.example.elegant.common.repository.CommonRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CartRepository extends CommonRepository<Cart, UUID> {
}
