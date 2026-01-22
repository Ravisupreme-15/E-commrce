package com.codingshuttle.e_commerce.model;

import com.codingshuttle.e_commerce.Enum.CARDTYPE;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "card")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(unique = true)
    String cardNo;

    int  cvv;

    Date  validTill;

    @Enumerated(EnumType.STRING)
    CARDTYPE cardtype;

    @ManyToOne
    @JoinColumn
    Customer customer;
}
