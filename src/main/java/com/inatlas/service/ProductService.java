package com.inatlas.service;

import java.util.List;

import com.inatlas.entities.Product;

public interface ProductService {

	public List<Product> getProductAll();

	public Product saveProduct(Product product);

	public Product getProductByProductName(String productName);

}
