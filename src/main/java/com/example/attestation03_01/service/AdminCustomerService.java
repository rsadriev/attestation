package com.example.attestation03_01.service;

import com.example.attestation03_01.model.Customer;
import com.example.attestation03_01.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminCustomerService {
    @Autowired
    CustomerRepository customerRepository;

    public Iterable<Customer> findSortedCustomer(){
        return customerRepository.findSortedCustomer();
    }

    public void save(Customer customer) {
        customerRepository.save(customer);
    }

    public void deleteById(Long id) {
        customerRepository.deleteById(id);
    }

    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }
}
