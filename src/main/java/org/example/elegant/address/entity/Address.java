package org.example.elegant.address.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.*;
import org.example.elegant.order.entity.Order;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Address {
    @Id
    private UUID id;
    private String street;
    private String city;
    private String country;
    private int zipCode;
    private String state;
    private String phoneNumber;
    @OneToOne
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Order order;
}
