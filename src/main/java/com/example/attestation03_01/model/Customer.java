package com.example.attestation03_01.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "customer_id_seq"
    )
    @SequenceGenerator(
            name = "customer_id_seq",
            allocationSize = 1
    )
    @Column(
            name = "id"
    )
    private Long id;

    @Column(
            name = "name"
    )
    private String name;

    @Column(
            name = "surname"
    )
    private String surname;

    @Column(
            name = "balance"
    )
    private Double balance;
}
