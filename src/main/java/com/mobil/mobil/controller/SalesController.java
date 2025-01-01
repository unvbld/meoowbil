package com.mobil.mobil.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mobil.mobil.model.Car;
import com.mobil.mobil.model.Sales;
import com.mobil.mobil.service.CarService;
import com.mobil.mobil.service.SalesService;

@Controller
@RequestMapping("/sales")
public class SalesController {

    @Autowired
    private SalesService salesService;

    @Autowired
    private CarService carService;

    @GetMapping
    public String listSales(Model model) {
        model.addAttribute("sales", salesService.getAllSales());
        return "sales"; // Tampilkan halaman daftar penjualan
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        List<Car> cars = carService.getAllCars();
        model.addAttribute("sale", new Sales());
        model.addAttribute("cars", cars);
        return "add-sale";
    }


    @PostMapping("/add")
    public String addSale(@ModelAttribute Sales sale, @RequestParam("mobil") Long carId) {
        Car car = carService.getCarById(carId); // Ambil data mobil berdasarkan ID
        if (car != null) {
            sale.setBrand(car.getBrand());
            sale.setModel(car.getModel());
            sale.setPrice(car.getPrice());
        }
        salesService.saveSale(sale); // Simpan data penjualan
        return "redirect:/sales";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Sales sale = salesService.getSaleById(id); // Ambil data penjualan berdasarkan ID
        if (sale == null) {
            return "redirect:/sales"; // Jika data tidak ditemukan, kembalikan ke halaman utama
        }
        List<Car> cars = carService.getAllCars();
        model.addAttribute("sale", sale);
        model.addAttribute("cars", cars);
        return "edit-sale"; // Tampilkan halaman edit penjualan
    }

    @PostMapping("/edit/{id}")
    public String editSale(@PathVariable Long id, @ModelAttribute Sales sale, @RequestParam("mobil") Long carId) {
        Car car = carService.getCarById(carId); // Ambil data mobil berdasarkan ID
        if (car != null) {
            sale.setBrand(car.getBrand());
            sale.setModel(car.getModel());
            sale.setPrice(car.getPrice());
        }
        sale.setId(id); // Set ID penjualan yang akan diupdate
        salesService.saveSale(sale); // Update data penjualan
        return "redirect:/sales";
    }

    @GetMapping("/delete/{id}")
    public String deleteSale(@PathVariable Long id) {
        salesService.deleteSale(id); // Hapus data penjualan berdasarkan ID
        return "redirect:/sales";
    }

    @GetMapping("/filter")
    public String filterSales(@RequestParam("start") String startDate, @RequestParam("end") String endDate,Model model) {
        try {
            List<Sales> filteredSales = salesService.filterSalesByDate(startDate, endDate);
            model.addAttribute("sales", filteredSales);
        } catch (Exception e) {
            model.addAttribute("error", "Invalid date format or range");
        }
        return "sales";
    }

}
