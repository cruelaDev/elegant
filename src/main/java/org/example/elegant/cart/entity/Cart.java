package org.example.elegant.cart.entity;
import jakarta.persistence.*;
import lombok.*;
import org.example.elegant.productSet.entity.ProductSet;
import org.example.elegant.user.entity.User;

import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Cart {
    @Id
    private UUID id;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "cart", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<ProductSet> products;
}
