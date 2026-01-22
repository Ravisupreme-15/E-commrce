package com.codingshuttle.e_commerce.repository;

import com.codingshuttle.e_commerce.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository extends JpaRepository<Seller,Integer> {


     public Seller findByEmail(String email);
}
