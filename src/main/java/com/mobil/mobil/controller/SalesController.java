package com.mobil.mobil.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mobil.mobil.model.sales;
import com.mobil.mobil.repository.SalesRepository;

@Controller
@RestController
@RequestMapping("/sales")
public class SalesController {

    @Autowired
    private SalesRepository salesRepository;

    @GetMapping("/{id}")
    public ResponseEntity<sales> getSalesById(@PathVariable Long id) {
        Optional<sales> salesData = salesRepository.findById(id);
        return salesData.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public sales createSales(@RequestBody sales salesData) {
        return salesRepository.save(salesData);
    }

    @PutMapping("/{id}")
    public ResponseEntity<sales> updateSales(@PathVariable Long id, @RequestBody sales salesDetails) {
        Optional<sales> salesData = salesRepository.findById(id);
        if (salesData.isPresent()) {
            sales salesToUpdate = salesData.get();
            salesToUpdate.setNamaSales(salesDetails.getNamaSales());
            salesToUpdate.setCar(salesDetails.getCar());
            salesToUpdate.setPenjualan(salesDetails.getPenjualan());
            salesToUpdate.setPembeli(salesDetails.getPembeli());
            salesToUpdate.setTanggal(salesDetails.getTanggal());
            return ResponseEntity.ok(salesRepository.save(salesToUpdate));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSales(@PathVariable Long id) {
        if (salesRepository.existsById(id)) {
            salesRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
