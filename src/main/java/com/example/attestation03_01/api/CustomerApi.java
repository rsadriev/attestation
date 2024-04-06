package com.example.attestation03_01.api;

import com.example.attestation03_01.dto.CustomerStockApiDto;
import com.example.attestation03_01.exception.ResourceNotFoundException;
import com.example.attestation03_01.model.Customer;
import com.example.attestation03_01.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer")
public class CustomerApi {
    @Autowired
    CustomerService customerService;

    @GetMapping()
    ResponseEntity<Iterable<Customer>> getCustomers() {
        return ResponseEntity.ok(customerService.apiFindAll());
    }

    @GetMapping("{id}")
    ResponseEntity<Customer> getCustomer(@PathVariable Long id) {
        return ResponseEntity.ok(customerService.findCustomerById(id).orElseThrow(ResourceNotFoundException::new));
    }

    @PostMapping("")
    ResponseEntity<Customer> createCustomer(@RequestBody Customer payload) {
        return ResponseEntity.ok(customerService.apiSaveCustomer(payload));
    }

    @PutMapping("")
    ResponseEntity<Void> updateCustomer(@RequestBody Customer payload) {
        customerService.apiUpdateCustomer(payload);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}")
    ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.apiDeleteCustomer(id);

        return ResponseEntity.noContent().build();
    }
}
