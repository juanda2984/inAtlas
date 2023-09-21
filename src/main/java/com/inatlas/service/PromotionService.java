package com.inatlas.service;

import java.util.List;

import com.inatlas.dto.ReceiptDto;
import com.inatlas.entities.SaleOrderProduct;

public interface PromotionService {
	ReceiptDto applyPromotions(List<SaleOrderProduct> saleOrderProducts);
}
