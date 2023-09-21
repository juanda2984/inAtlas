package com.inatlas.service;

import java.util.List;

import com.inatlas.entities.SaleOrder;
import com.inatlas.entities.SaleOrderProduct;

public interface SaleOrderProductService {

	public void save(List<SaleOrderProduct> saleOrderProducts, SaleOrder saleOrder);

}
