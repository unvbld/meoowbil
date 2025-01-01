package com.mobil.mobil.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String brand;
    private String model;
    private String rangka;
    private String mesin;
    private String plat;
    private double kapasitas;
    private int year;
    private double price;
    private String imageFileName;

    @Enumerated(EnumType.STRING)
    private CarType type;

    public Car() {}

    public Car(String brand, String model, String rangka, String mesin, String plat, double kapasitas, int year, double price, CarType type) {
        this.brand = brand;
        this.model = model;
        this.rangka = rangka;
        this.mesin = mesin;
        this.plat = plat;
        this.kapasitas = kapasitas;
        this.year = year;
        this.price = price;
        this.type = type;
    }

}
