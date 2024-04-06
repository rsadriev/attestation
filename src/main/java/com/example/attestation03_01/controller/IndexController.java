package com.example.attestation03_01.controller;

import com.example.attestation03_01.repository.CustomerRepository;
import com.example.attestation03_01.repository.CustomerStockRepository;
import com.example.attestation03_01.repository.StockRepository;
import com.example.attestation03_01.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/")
public class IndexController {
    @Autowired
    IndexService indexService;

    @GetMapping("")
    public String index() {
        return "redirect:/index";
    }

    @GetMapping("index")
    public String getIndex(Model model) {
        model.addAttribute("usersCount", indexService.customerRepositoryCount());
        model.addAttribute("stocksCount", indexService.stockRepositoryCount());
        model.addAttribute("transactionsCount", indexService.customerStockRepositoryCount());

        return "index";
    }
}
