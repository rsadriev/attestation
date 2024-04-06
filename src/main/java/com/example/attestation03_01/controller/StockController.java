package com.example.attestation03_01.controller;

import com.example.attestation03_01.model.Stock;
import com.example.attestation03_01.repository.CustomerStockRepository;
import com.example.attestation03_01.repository.StockRepository;
import com.example.attestation03_01.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/stock")
public class StockController {
    @Autowired
    StockService stockService;

    @GetMapping("{id}")
    public String getCustomer(Model model, @PathVariable Long id) {

        Stock stock;
        try {
            stock = stockService.findStockById(id).orElseThrow();
        } catch (Exception e) {
            return "redirect:/stocks";
        }

        model.addAttribute("stock", stock);
        model.addAttribute("stockList", stockService.findCustomerByStockId(id));

        return "stock";
    }
}
