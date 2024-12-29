package com.mobil.mobil.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mobil.mobil.model.Car;


public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findByBrandContainingIgnoreCaseOrModelContainingIgnoreCase(String brand, String model);
}

