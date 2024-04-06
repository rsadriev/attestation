package com.example.attestation03_01.service;

import com.example.attestation03_01.model.Stock;
import com.example.attestation03_01.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminStockService {
    @Autowired
    StockRepository stockRepository;

    public Iterable<Stock> findSortedStock() {
        return stockRepository.findSortedStock();
    }

    public void save(Stock stock) {
        stockRepository.save(stock);
    }

    public void deleteById(Long id) {
        stockRepository.deleteById(id);
    }

    public Optional<Stock> findById(Long id) {
        return stockRepository.findById(id);
    }
}
