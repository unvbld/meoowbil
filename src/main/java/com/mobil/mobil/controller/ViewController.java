package com.mobil.mobil.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {
    @GetMapping("/sales")
    public String getSalesPage() {
        return "sales"; // Mengarah ke sales.html di folder templates
    }
}
