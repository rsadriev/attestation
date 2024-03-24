package com.example.attestation03_01.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "stock")
public class Stock {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "stock_id_seq"
    )
    @SequenceGenerator(
            name = "stock_id_seq",
            allocationSize = 1
    )
    @Column(
            name = "id"
    )
    private Long id;

    @Column(
            name = "description"
    )
    private String description;

    @Column(
            name = "price"
    )
    private Double price;

    @Column(
            name = "count"
    )
    private Long count;
}
