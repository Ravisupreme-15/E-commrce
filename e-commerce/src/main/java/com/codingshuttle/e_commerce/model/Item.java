package com.codingshuttle.e_commerce.model;

import jakarta.persistence.*;
import jakarta.persistence.criteria.Order;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.sound.sampled.Port;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "item")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    int requiredQuantity;


    @ManyToOne
    @JoinColumn
    Cart cart;


    @ManyToOne
    @JoinColumn
    OrderEntity orderEntity;

    @ManyToOne
    @JoinColumn
    Product product;

}
