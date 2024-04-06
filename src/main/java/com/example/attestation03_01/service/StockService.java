package com.example.attestation03_01.service;

import com.example.attestation03_01.exception.ResourceNotFoundException;
import com.example.attestation03_01.model.CustomerStock;
import com.example.attestation03_01.model.Stock;
import com.example.attestation03_01.repository.CustomerStockRepository;
import com.example.attestation03_01.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StockService {
    @Autowired
    CustomerStockRepository customerStockRepository;

    @Autowired
    StockRepository stockRepository;

    public Optional<Stock> findStockById(Long id) {
        return stockRepository.findById(id);
    }

    public List<CustomerStock> findCustomerByStockId(Long id) {
        return customerStockRepository.findByStock(id);
    }

    public Iterable<Stock> apiFindStocks() {
        return stockRepository.findAll();
    }

    public Stock apiSaveStock(Stock payload) {
        return stockRepository.save(payload);
    }

    public void apiUpdateStock(Stock payload) {
        stockRepository.findById(payload.getId()).orElseThrow(ResourceNotFoundException::new);

        stockRepository.save(payload);
    }

    public void apiDeleteStock(Long id) {
        stockRepository.deleteById(id);
    }
}
