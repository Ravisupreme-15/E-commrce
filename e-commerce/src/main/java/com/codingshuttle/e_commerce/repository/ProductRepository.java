package com.codingshuttle.e_commerce.repository;

import com.codingshuttle.e_commerce.Enum.ProductCategory;
import com.codingshuttle.e_commerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Integer> {

    public List<Product> findByCategoryAndPriceGreaterThan( ProductCategory category, int price);
}
