package com.mobil.mobil.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mobil.mobil.model.Sales;

@Repository
public interface SalesRepository extends JpaRepository<Sales, Long> {
    List<Sales> findByTanggalPenjualanBetween(Date startDate, Date endDate);

}
