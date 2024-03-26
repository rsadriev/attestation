package com.example.attestation03_01.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "customer_stock")
public class CustomerStock {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "customer_stock_seq"
    )
    @SequenceGenerator(
            name = "customer_stock_seq",
            allocationSize = 1
    )
    private Long id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "stock_id")
    private Stock stock;

    @Column(name = "price")
    private Double price;

    @Column(name = "count")
    private Long count;
}
