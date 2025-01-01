package com.mobil.mobil.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mobil.mobil.model.sales;

@Repository
public interface SalesRepository extends JpaRepository<sales, Long> {
    // Additional query methods can be defined here if needed
}