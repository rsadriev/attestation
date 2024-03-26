package com.example.attestation03_01.repository;

import com.example.attestation03_01.model.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    @Query("select c from Customer c order by c.id asc")
    Iterable<Customer> findSortedCustomer();
}
