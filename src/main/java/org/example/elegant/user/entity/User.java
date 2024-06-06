package org.example.elegant.user.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.elegant.cart.entity.Cart;
import org.example.elegant.order.entity.Order;
import org.example.elegant.rating.entity.Rating;
import org.example.elegant.user.permission.entity.Permission;
import org.example.elegant.user.role.entity.Role;
import org.example.elegant.wishlist.entity.Wishlist;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "`user`")
public class User implements UserDetails {
    @Id
    private UUID id;
    private String firstName;
    private String lastName;
    private String displayName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(columnDefinition = "boolean default false")
    private boolean isVerified;

    @CreatedDate
    private LocalDateTime created;
    @LastModifiedDate
    private LocalDateTime updated;

    @OneToOne(mappedBy = "user")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Cart cart;

    @OneToOne(mappedBy = "user")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Wishlist wishlist;

    @OneToMany(mappedBy = "user")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Rating> ratings;

    @OneToMany(mappedBy = "user")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Order> orders;


    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_permission",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    private Set<Permission> permissions;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Stream<Permission> rolePermissionStream = roles.stream()
                .map(Role::getPermissions)
                .flatMap(Collection::stream);

        Stream<Permission> permissionStream = Stream.concat(rolePermissionStream, permissions.stream());

        Set<SimpleGrantedAuthority> collect = permissionStream
                .map(permission -> new SimpleGrantedAuthority(permission.getName()))
                .collect(Collectors.toSet());

        return collect;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
