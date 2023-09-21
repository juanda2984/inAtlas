package com.inatlas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inatlas.entities.SaleOrderProduct;
import com.inatlas.entities.SaleOrderProductPK;

@Repository
public interface SaleOrderProductRepository extends JpaRepository<SaleOrderProduct, SaleOrderProductPK> {

}
