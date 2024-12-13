package com.mobil.mobil.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.mobil.mobil.model.Car;
import com.mobil.mobil.service.CarService;



@Controller
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping
    public String listCars(Model model) {
        model.addAttribute("cars", carService.getAllCars());
        return "cars";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("car", new Car());
        return "add-car";
    }

    @PostMapping("/add")
    public String addCar(@ModelAttribute Car car, @RequestParam("image") MultipartFile image) {
        try {
            if (!image.isEmpty()) {
                String fileName = carService.saveImage(image);
                car.setImageFileName(fileName);
            }
            carService.saveCar(car);
        } catch (IOException e) {
            // Handle the error appropriately
            
        }
        return "redirect:/cars";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Car car = carService.getCarById(id);
        model.addAttribute("car", car);
        return "edit-car";
    }

    @PostMapping("/edit/{id}")
    public String editCar(@PathVariable Long id, @ModelAttribute Car car, @RequestParam("image") MultipartFile image) {
        try {
            if (!image.isEmpty()) {
                String fileName = carService.saveImage(image);
                car.setImageFileName(fileName);
            } else {
                // Preserve the existing image if no new image is uploaded
                Car existingCar = carService.getCarById(id);
                car.setImageFileName(existingCar.getImageFileName());
            }
            car.setId(id);
            carService.saveCar(car);
        } catch (IOException e) {
            // Handle the error appropriately
            
        }
        return "redirect:/cars";
    }

    @GetMapping("/delete/{id}")
    public String deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
        return "redirect:/cars";
    }

}

