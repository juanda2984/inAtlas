package com.inatlas.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inatlas.entities.SaleOrder;
import com.inatlas.entities.SaleOrderProduct;
import com.inatlas.repository.SaleOrderProductRepository;
import com.inatlas.service.SaleOrderProductService;

@Service
public class SaleOrderProductServiceImpl implements SaleOrderProductService {

	private final SaleOrderProductRepository saleOrderProductRepository;

	@Autowired
	public SaleOrderProductServiceImpl(SaleOrderProductRepository saleOrderProductRepository) {
		this.saleOrderProductRepository = saleOrderProductRepository;
	}

	@Override
	public void save(List<SaleOrderProduct> saleOrderProducts, SaleOrder saleOrder) {
		for (SaleOrderProduct saleOrderProduct : saleOrderProducts) {
			saleOrderProduct.setSaleOrder(saleOrder);
			saleOrderProduct = saleOrderProductRepository.save(saleOrderProduct);
		}
	}
}
