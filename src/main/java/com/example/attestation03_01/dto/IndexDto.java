package com.example.attestation03_01.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class IndexDto {
    private Long usersCount;

    private Long stocksCount;

    private Long transactionsCount;
}
