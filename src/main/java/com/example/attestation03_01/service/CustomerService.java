package com.example.attestation03_01.service;

import com.example.attestation03_01.dto.CustomerStockApiDto;
import com.example.attestation03_01.dto.CustomerStockDto;
import com.example.attestation03_01.exception.ResourceBadRequestException;
import com.example.attestation03_01.exception.ResourceNotFoundException;
import com.example.attestation03_01.model.Customer;
import com.example.attestation03_01.model.CustomerStock;
import com.example.attestation03_01.model.Stock;
import com.example.attestation03_01.repository.CustomerRepository;
import com.example.attestation03_01.repository.CustomerStockRepository;
import com.example.attestation03_01.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CustomerStockRepository customerStockRepository;

    @Autowired
    StockRepository stockRepository;

    public Optional<Customer> findCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    public List<CustomerStock> findStockByCustomerId(Long id) {
        return customerStockRepository.findByCustomer(id);
    }

    public List<Stock> findActiveStocks() {
        return stockRepository.findActiveStock();
    }

    public void addTransaction(CustomerStockDto stock, Long id) {
        var customer = customerRepository.findById(id).orElseThrow();
        var repoStock = stockRepository.findById(stock.getStockId()).orElseThrow();

        CustomerStock transaction = new CustomerStock();

        transaction.setCustomer(customer);
        transaction.setStock(repoStock);
        transaction.setCount(stock.getCount());
        transaction.setPrice(stock.getCount() * repoStock.getPrice());

        double customerBalance = customer.getBalance() - stock.getCount() * repoStock.getPrice();
        long stockCount = repoStock.getCount() - stock.getCount();

        if (customerBalance >= 0 && stockCount >= 0) {

            customer.setBalance(customerBalance);
            repoStock.setCount(stockCount);

            customerRepository.save(customer);
            stockRepository.save(repoStock);
            customerStockRepository.save(transaction);
        }
    }

    public Iterable<Customer> apiFindAll() {
        return customerRepository.findAll();
    }

    public Customer apiSaveCustomer(Customer payload) {
        return customerRepository.save(payload);
    }

    public void apiUpdateCustomer(Customer payload) {
        customerRepository.findById(payload.getId()).orElseThrow(ResourceNotFoundException::new);

        customerRepository.save(payload);
    }

    public void apiDeleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    public Iterable<CustomerStockApiDto> apiFindStocksByCustomer(Long id) {
        customerRepository.findById(id).orElseThrow(ResourceNotFoundException::new);

        return findStockByCustomerId(id)
                .stream()
                .map(it -> new CustomerStockApiDto(it.getId(), it.getStock().getId(), it.getPrice(), it.getCount()))
                .collect(Collectors.toList());
    }

    public CustomerStockApiDto apiFindStocksByIdCustomer(Long id, Long tId) {
        customerRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        var stock = findStockByCustomerId(id)
                .stream().
                filter(it -> Objects.equals(it.getId(), tId))
                .toList();

        if (stock.isEmpty()) {
            throw new ResourceNotFoundException();
        }

        return new CustomerStockApiDto(stock.get(0).getId(), stock.get(0).getStock().getId(), stock.get(0).getPrice(), stock.get(0).getCount());
    }

    public CustomerStockApiDto apiAddTransaction(Long id, CustomerStockApiDto payload) {
        var customer = customerRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        var repoStock = stockRepository.findById(payload.getStockId()).orElseThrow(ResourceNotFoundException::new);

        CustomerStock transaction = new CustomerStock();

        transaction.setCustomer(customer);
        transaction.setStock(repoStock);
        transaction.setCount(payload.getCount());
        transaction.setPrice(payload.getCount() * repoStock.getPrice());

        double customerBalance = customer.getBalance() - payload.getCount() * repoStock.getPrice();
        long stockCount = repoStock.getCount() - payload.getCount();

        if (customerBalance >= 0 && stockCount >= 0) {

            customer.setBalance(customerBalance);
            repoStock.setCount(stockCount);

            customerRepository.save(customer);
            stockRepository.save(repoStock);
            transaction = customerStockRepository.save(transaction);
        } else {
            throw new ResourceBadRequestException();
        }

        return new CustomerStockApiDto(transaction.getId(),
                transaction.getStock().getId(),
                transaction.getPrice(),
                transaction.getCount());
    }

    public void apiDeleteTransaction(Long id, Long tId) {
        customerRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        customerStockRepository.findById(tId).orElseThrow(ResourceNotFoundException::new);

        var stock = findStockByCustomerId(id)
                .stream().
                filter(it -> Objects.equals(it.getId(), tId))
                .toList();

        if (stock.isEmpty()) {
            throw new ResourceBadRequestException();
        }

        customerStockRepository.deleteById(tId);
    }
}
