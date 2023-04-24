package com.generalstore.repository;

import com.generalstore.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findByProductNameStartingWith(String productName);

    boolean existsByProductNameAndCost(String productName, Double cost);
}
