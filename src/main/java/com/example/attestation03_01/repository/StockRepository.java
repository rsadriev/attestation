package com.example.attestation03_01.repository;

import com.example.attestation03_01.model.Stock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StockRepository extends CrudRepository<Stock, Long> {

    @Query("select s from Stock s where s.count > 0 order by s.id asc")
    List<Stock> findActiveStock();


    @Query("select s from Stock s order by s.id asc")
    Iterable<Stock> findSortedStock();
}
