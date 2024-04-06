package com.example.attestation03_01.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerStockApiDto {
    private Long id;

    private Long stockId;

    private Double price;

    private Long count;
}
