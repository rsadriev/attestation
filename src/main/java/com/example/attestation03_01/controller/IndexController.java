package com.example.attestation03_01.controller;

import com.example.attestation03_01.repository.CustomerRepository;
import com.example.attestation03_01.repository.CustomerStockRepository;
import com.example.attestation03_01.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

@Controller
public class IndexController {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CustomerStockRepository customerStockRepository;

    @Autowired
    StockRepository stockRepository;

    @RequestMapping("/")
    public String index() {
        return "redirect:/index";
    }

    @RequestMapping("index")
    public String getIndex(Model model) {
        model.addAttribute("usersCount", customerRepository.count());
        model.addAttribute("stocksCount", stockRepository.count());
        model.addAttribute("transactionsCount", customerStockRepository.count());

        return "index";
    }
}
