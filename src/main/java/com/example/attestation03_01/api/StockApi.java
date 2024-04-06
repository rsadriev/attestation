package com.example.attestation03_01.api;

import com.example.attestation03_01.exception.ResourceNotFoundException;
import com.example.attestation03_01.model.Stock;
import com.example.attestation03_01.service.CustomerService;
import com.example.attestation03_01.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stock")
public class StockApi {
    @Autowired
    StockService stockService;

    @Autowired
    CustomerService customerService;

    @GetMapping("active")
    ResponseEntity<Iterable<Stock>> getActiveStocks() {
        return ResponseEntity.ok(customerService.findActiveStocks());
    }

    @GetMapping()
    ResponseEntity<Iterable<Stock>> getStocks() {
        return ResponseEntity.ok(stockService.apiFindStocks());
    }

    @GetMapping("{id}")
    ResponseEntity<Stock> getStock(@PathVariable Long id) {
        return ResponseEntity.ok(stockService.findStockById(id).orElseThrow(ResourceNotFoundException::new));
    }

    @PostMapping("")
    ResponseEntity<Stock> createStock(@RequestBody Stock payload) {
        return ResponseEntity.ok(stockService.apiSaveStock(payload));
    }

    @PutMapping("")
    ResponseEntity<Void> updateStock(@RequestBody Stock payload) {
        stockService.apiUpdateStock(payload);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}")
    ResponseEntity<Void> deleteStock(@PathVariable Long id) {
        stockService.apiDeleteStock(id);

        return ResponseEntity.noContent().build();
    }
}
