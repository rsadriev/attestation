package com.example.attestation03_01.controller;

import com.example.attestation03_01.model.Stock;
import com.example.attestation03_01.repository.CustomerStockRepository;
import com.example.attestation03_01.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StockController {
    @Autowired
    CustomerStockRepository customerStockRepository;

    @Autowired
    StockRepository stockRepository;

    @RequestMapping("/stock/{id}")
    public String getCustomer(Model model, @PathVariable Long id) {

        Stock stock;
        try {
            stock = stockRepository.findById(id).orElseThrow();
        } catch (Exception e) {
            return "redirect:/stocks";
        }

        model.addAttribute("stock", stock);
        model.addAttribute("stockList", customerStockRepository.findByStock(stock.getId()));

        return "stock";
    }
}
