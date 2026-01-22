package com.codingshuttle.e_commerce.repository;

import com.codingshuttle.e_commerce.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {


    public Customer findByMobile(String mobile);

    public Customer findByEmail(String email);
}
