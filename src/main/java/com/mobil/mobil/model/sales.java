package com.mobil.mobil.model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class sales {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String namaSales;
    private String mobil;
    private double penjualan;
    private String pembeli;
    private Date tanggal;

    public sales(String ns, String m,double p, String pem, Date t){
        this.namaSales = ns;
        this.mobil = m;
        this.penjualan = p;
        this.pembeli = pem;
        this.tanggal = t;
    }

    // Setter
    public void setId(Long id){
        this.id = id;
    }

    public void setNamaSales(String ns){
        this.namaSales = ns;
    }

    public void setPenjualan(double p){
        this.penjualan = p;
    }

    public void setPembeli(String p){
        this.pembeli = p;
    }

    public void setTanggal(Date t){
        this.tanggal = t;
    }

    public void setCar(String c){
        this.mobil = c;
    }

    // Getter
    public Long getId(){
        return this.id;
    }

    public String getNamaSales(){
        return this.namaSales;
    }

    public double getPenjualan(){
        return this.penjualan;
    }

    public String getPembeli(){
        return this.pembeli;
    }

    public Date getTanggal(){
        return this.tanggal;
    }

    public String getCar(){
        return this.mobil;
    }
}

