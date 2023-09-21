package com.inatlas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inatlas.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	Product findByProductName(String productName);

}
