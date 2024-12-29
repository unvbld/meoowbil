package com.mobil.mobil.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mobil.mobil.model.Car;
import com.mobil.mobil.repository.CarRepository;


@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Car saveCar(Car car) {
        return carRepository.save(car);
    }

    public Car getCarById(Long id) {
        return carRepository.findById(id).orElse(null);
    }

    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }

    public String saveImage(MultipartFile file) throws IOException {
        String folder = "./src/main/resources/static/images/";
        byte[] bytes = file.getBytes();
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Path path = Paths.get(folder + fileName);
        Files.write(path, bytes);
        return fileName;
    }

    public List<Car> searchCars(String query) {
        if (query != null && !query.isEmpty()) {
            return carRepository.findByBrandContainingIgnoreCaseOrModelContainingIgnoreCase(query, query);
        }
        return carRepository.findAll(); // Jika query kosong, kembalikan semua mobil
    }
}


