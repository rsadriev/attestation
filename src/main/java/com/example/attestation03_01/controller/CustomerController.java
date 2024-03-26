package com.example.attestation03_01.controller;

import com.example.attestation03_01.dto.CustomerStockDto;
import com.example.attestation03_01.model.CustomerStock;
import com.example.attestation03_01.repository.CustomerRepository;
import com.example.attestation03_01.repository.CustomerStockRepository;
import com.example.attestation03_01.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import java.util.NoSuchElementException;

@Controller
public class CustomerController {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CustomerStockRepository customerStockRepository;

    @Autowired
    StockRepository stockRepository;

    @RequestMapping("/customer/{id}")
    public String getCustomer(Model model, @PathVariable Long id) {
        try {
            var customer = customerRepository.findById(id).orElseThrow();

            model.addAttribute("customer", customer);
            model.addAttribute("customerOrders", customerStockRepository.findByCustomer(id));
            model.addAttribute("stockList", stockRepository.findActiveStock());
            model.addAttribute("stock", new CustomerStockDto());
        } catch (NoSuchElementException e) {
            model.addAttribute("id", id);
            return "nocustomer";
        }

        return "customer";
    }

    @PostMapping("/customer/{id}/addStock")
    public String addStock(@ModelAttribute("stock") CustomerStockDto stock, @PathVariable Long id) {
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

        return "redirect:/customer/{id}";
    }

}
