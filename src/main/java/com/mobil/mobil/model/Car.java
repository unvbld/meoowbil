package com.mobil.mobil.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
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

    //Setter Getter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getRangka() {
        return rangka;
    }

    public void setRangka(String rangka) {
        this.rangka = rangka;
    }

    public String getMesin() {
        return mesin;
    }

    public void setMesin(String mesin) {
        this.mesin = mesin;
    }

    public String getPlat() {
        return plat;
    }

    public void setPlat(String plat) {
        this.plat = plat;
    }

    public double getKapasitas() {
        return kapasitas;
    }

    public void setKapasitas(double kapasitas) {
        this.kapasitas = kapasitas;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }

    public CarType getType() {
        return type;
    }

    public void setType(CarType type) {
        this.type = type;
    }
}
