package com.inatlas.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inatlas.entities.Product;
import com.inatlas.repository.ProductRepository;
import com.inatlas.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;

	@Autowired
	public ProductServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	@Override
	public List<Product> getProductAll() {
		List<Product> products = new ArrayList<Product>();
		productRepository.findAll().forEach(products::add);
		return products;
	}

	@Override
	public Product saveProduct(Product product) {
		return productRepository.save(product);
	}

	@Override
	public Product getProductByProductName(String productName) {
		return productRepository.findByProductName(productName);
	}

}
