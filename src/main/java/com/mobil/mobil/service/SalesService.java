package com.mobil.mobil.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobil.mobil.model.Sales;
import com.mobil.mobil.repository.SalesRepository;

@Service
public class SalesService {

    @Autowired
    private SalesRepository salesRepository;

    public List<Sales> getAllSales() {
        return salesRepository.findAll();
    }

    public Sales saveSale(Sales sale) {
        return salesRepository.save(sale);
    }

    public Sales getSaleById(Long id) {
        return salesRepository.findById(id).orElse(null);
    }

    public void deleteSale(Long id) {
        salesRepository.deleteById(id);
    }

    public List<Sales> filterSalesByDate(String startDate, String endDate) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date start = dateFormat.parse(startDate);
        Date end = dateFormat.parse(endDate);
        return salesRepository.findByTanggalPenjualanBetween(start, end);
    }

}
