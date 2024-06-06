package org.example.elegant.wishlist.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.elegant.product.entity.Product;
import org.example.elegant.user.entity.User;

import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Wishlist {
    @Id
    private UUID id;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "wishlist_product",
            joinColumns = @JoinColumn(name = "wishlist_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private Set<Product> products;
}
