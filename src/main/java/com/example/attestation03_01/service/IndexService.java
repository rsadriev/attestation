package com.example.attestation03_01.service;

import com.example.attestation03_01.dto.IndexDto;
import com.example.attestation03_01.repository.CustomerRepository;
import com.example.attestation03_01.repository.CustomerStockRepository;
import com.example.attestation03_01.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IndexService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CustomerStockRepository customerStockRepository;

    @Autowired
    StockRepository stockRepository;

    public long customerRepositoryCount() {
        return customerRepository.count();
    }

    public long customerStockRepositoryCount() {
        return customerStockRepository.count();
    }

    public long stockRepositoryCount() {
        return stockRepository.count();
    }

    public IndexDto apiStats() {
        return new IndexDto(customerRepository.count(), stockRepository.count(), customerStockRepository.count());
    }
}
