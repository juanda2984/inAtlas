package com.inatlas.service;

import java.util.List;

import com.inatlas.dto.ReceiptDto;
import com.inatlas.entities.SaleOrder;
import com.inatlas.entities.SaleOrderProduct;

public interface SaleOrderService {
	public List<SaleOrder> getSaleOrderAll();

	public ReceiptDto saveSaleOrder(List<SaleOrderProduct> saleOrderProducts);
}
