package org.example.elegant.productSet.entity;
import jakarta.persistence.*;
import lombok.*;
import org.example.elegant.cart.entity.Cart;
import org.example.elegant.order.entity.Order;
import org.example.elegant.product.entity.Product;

import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ProductSet {
    @Id
    private UUID id;
    private int quantity;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToMany(mappedBy = "products")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Order> orders;
}

