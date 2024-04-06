package com.example.attestation03_01.controller;

import com.example.attestation03_01.model.Customer;
import com.example.attestation03_01.repository.CustomerRepository;
import com.example.attestation03_01.service.AdminCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.Optional;

@Controller
@RequestMapping("/customer")
public class AdminCustomerController {

    @Autowired
    AdminCustomerService adminCustomerService;

    @GetMapping()
    public String customers(Model model) {
        var customerList = adminCustomerService.findSortedCustomer();

        model.addAttribute("customers", customerList);
        return "customers";
    }

    @GetMapping("/add")
    public String addCustomer(Model model) {
        Customer customer = new Customer();
        model.addAttribute("form", customer);

        return "addcustomer";
    }

    @PostMapping("/create")
    public String createCustomer(@ModelAttribute("addCustomer") Customer customer) {
        adminCustomerService.save(customer);

        return "redirect:/customer";
    }

    @GetMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable Long id) {
        adminCustomerService.deleteById(id);

        return "redirect:/customer";
    }

    @GetMapping("/edit/{id}")
    public String editCustomer(Model model, @PathVariable Long id) {
        Optional<Customer> customer = adminCustomerService.findById(id);

        model.addAttribute("form", customer);

        return "addcustomer";
    }
}
