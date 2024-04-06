package com.example.attestation03_01.controller;

import com.example.attestation03_01.dto.CustomerStockDto;
import com.example.attestation03_01.model.CustomerStock;
import com.example.attestation03_01.repository.CustomerRepository;
import com.example.attestation03_01.repository.CustomerStockRepository;
import com.example.attestation03_01.repository.StockRepository;
import com.example.attestation03_01.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.NoSuchElementException;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @GetMapping("{id}")
    public String getCustomer(Model model, @PathVariable Long id) {
        try {
            var customer = customerService.findCustomerById(id).orElseThrow();

            model.addAttribute("customer", customer);
            model.addAttribute("customerOrders", customerService.findStockByCustomerId(id));
            model.addAttribute("stockList", customerService.findActiveStocks());
            model.addAttribute("stock", new CustomerStockDto());
        } catch (NoSuchElementException e) {
            model.addAttribute("id", id);
            return "nocustomer";
        }

        return "customer";
    }

    @PostMapping("{id}/addStock")
    public String addStock(@ModelAttribute("stock") CustomerStockDto stock, @PathVariable Long id) {
        customerService.addTransaction(stock, id);

        return "redirect:/customer/{id}";
    }

}
