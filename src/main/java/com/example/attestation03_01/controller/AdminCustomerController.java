package com.example.attestation03_01.controller;

import com.example.attestation03_01.model.Customer;
import com.example.attestation03_01.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import java.util.Optional;

@Controller
@RequestMapping("/customer")
public class AdminCustomerController {

    @Autowired
    CustomerRepository customerRepository;

    @RequestMapping("")
    public String customers(Model model) {
        var customerList = customerRepository.findSortedCustomer();

        model.addAttribute("customers", customerList);
        return "customers";
    }

    @RequestMapping("/add")
    public String addCustomer(Model model) {
        Customer customer = new Customer();
        model.addAttribute("form", customer);

        return "addcustomer";
    }

    @RequestMapping("/create")
    public String createCustomer(@ModelAttribute("addCustomer") Customer customer) {
        customerRepository.save(customer);

        return "redirect:/customer";
    }

    @RequestMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable Long id) {
        customerRepository.deleteById(id);

        return "redirect:/customer";
    }

    @RequestMapping("/edit/{id}")
    public String editCustomer(Model model, @PathVariable Long id) {
        Optional<Customer> customer = customerRepository.findById(id);

        model.addAttribute("form", customer);

        return "addcustomer";
    }
}
