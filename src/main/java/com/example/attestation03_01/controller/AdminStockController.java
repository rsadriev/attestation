package com.example.attestation03_01.controller;

import com.example.attestation03_01.model.Stock;
import com.example.attestation03_01.repository.StockRepository;
import com.example.attestation03_01.service.AdminStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/stock")
public class AdminStockController {
    @Autowired
    AdminStockService adminStockService;

    @GetMapping()
    public String stocks(Model model) {
        var stockList = adminStockService.findSortedStock();

        model.addAttribute("stocks", stockList);
        return "stocks";
    }

    @GetMapping("/add")
    public String addStock(Model model) {
        Stock stock = new Stock();
        model.addAttribute("form", stock);

        return "addstock";
    }

    @PostMapping("/create")
    public String createStock(@ModelAttribute("addCustomer") Stock stock) {
        adminStockService.save(stock);

        return "redirect:/stock";
    }

    @GetMapping("/delete/{id}")
    public String deleteStock(@PathVariable Long id) {
        adminStockService.deleteById(id);

        return "redirect:/stock";
    }

    @GetMapping("/edit/{id}")
    public String editStock(Model model, @PathVariable Long id) {
        Optional<Stock> stock = adminStockService.findById(id);

        model.addAttribute("form", stock);

        return "addstock";
    }
}
