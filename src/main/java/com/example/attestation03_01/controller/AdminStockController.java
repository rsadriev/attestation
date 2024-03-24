package com.example.attestation03_01.controller;

import com.example.attestation03_01.model.Stock;
import com.example.attestation03_01.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/stock")
public class AdminStockController {
    @Autowired
    StockRepository stockRepository;

    @RequestMapping("")
    public String stocks(Model model) {
        var stockList = stockRepository.findSortedStock();

        model.addAttribute("stocks", stockList);
        return "stocks";
    }

    @RequestMapping("/add")
    public String addStock(Model model) {
        Stock stock = new Stock();
        model.addAttribute("form", stock);

        return "addstock";
    }

    @RequestMapping("/create")
    public String createStock(@ModelAttribute("addCustomer") Stock stock) {
        stockRepository.save(stock);

        return "redirect:/stock";
    }

    @RequestMapping("/delete/{id}")
    public String deleteStock(@PathVariable Long id) {
        stockRepository.deleteById(id);

        return "redirect:/stock";
    }

    @RequestMapping("/edit/{id}")
    public String editStock(Model model, @PathVariable Long id) {
        Optional<Stock> stock = stockRepository.findById(id);

        model.addAttribute("form", stock);

        return "addstock";
    }
}
