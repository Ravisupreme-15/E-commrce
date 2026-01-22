package com.codingshuttle.e_commerce.repository;

import com.codingshuttle.e_commerce.model.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderEntityRepository extends JpaRepository<OrderEntity,Integer> {
}
