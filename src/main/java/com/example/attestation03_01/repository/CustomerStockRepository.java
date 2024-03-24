package com.example.attestation03_01.repository;

import com.example.attestation03_01.model.CustomerStock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerStockRepository extends CrudRepository<CustomerStock, Long> {

    @Query("select c from CustomerStock c where c.customer.id = ?1")
    List<CustomerStock> findByCustomer(Long id);

    @Query("select c from CustomerStock c where c.stock.id = ?1")
    List<CustomerStock> findByStock(Long id);
}
