package com.mobil.mobil.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.mobil.mobil.model.Car;

public interface CarRepository extends JpaRepository<Car, Long> {
}
