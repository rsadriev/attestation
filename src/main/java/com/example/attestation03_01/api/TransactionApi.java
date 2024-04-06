package com.example.attestation03_01.api;

import com.example.attestation03_01.dto.CustomerStockApiDto;
import com.example.attestation03_01.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer")
public class TransactionApi {
    @Autowired
    CustomerService customerService;

    @GetMapping("{id}/stock")
    ResponseEntity<Iterable<CustomerStockApiDto>> getStocks(@PathVariable Long id) {
        return ResponseEntity.ok(customerService.apiFindStocksByCustomer(id));
    }

    @GetMapping("{id}/stock/{tId}")
    ResponseEntity<CustomerStockApiDto> getStock(@PathVariable Long id, @PathVariable Long tId) {
        return ResponseEntity.ok(customerService.apiFindStocksByIdCustomer(id, tId));
    }

    @PostMapping("{id}/stock")
    ResponseEntity<CustomerStockApiDto> createTransaction(@PathVariable Long id, @RequestBody CustomerStockApiDto payload) {
        return ResponseEntity.ok(customerService.apiAddTransaction(id, payload));
    }

    @DeleteMapping("{id}/stock/{tId}")
    ResponseEntity<Void> deleteTransaction(@PathVariable Long id, @PathVariable Long tId) {
        customerService.apiDeleteTransaction(id, tId);

        return ResponseEntity.noContent().build();
    }
}
