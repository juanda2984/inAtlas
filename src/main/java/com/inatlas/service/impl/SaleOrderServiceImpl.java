package com.inatlas.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inatlas.dto.ReceiptDto;
import com.inatlas.entities.SaleOrder;
import com.inatlas.entities.SaleOrderProduct;
import com.inatlas.repository.SaleOrderRepository;
import com.inatlas.service.PromotionService;
import com.inatlas.service.SaleOrderProductService;
import com.inatlas.service.SaleOrderService;

@Service
public class SaleOrderServiceImpl implements SaleOrderService {
	private final SaleOrderRepository saleOrderRepository;

	private final SaleOrderProductService saleOrderProductService;

	private final PromotionService promotionService;

	@Autowired
	public SaleOrderServiceImpl(SaleOrderRepository saleOrderRepository,
			SaleOrderProductService saleOrderProductService, PromotionService promotionService) {
		this.saleOrderRepository = saleOrderRepository;
		this.saleOrderProductService = saleOrderProductService;
		this.promotionService = promotionService;
	}

	@Override
	public List<SaleOrder> getSaleOrderAll() {
		List<SaleOrder> saleOrders = saleOrderRepository.findAll();
		return saleOrders;
	}

	@Override
	public ReceiptDto saveSaleOrder(List<SaleOrderProduct> saleOrderProducts) {
		ReceiptDto receiptDto = promotionService.applyPromotions(saleOrderProducts);
		SaleOrder saleOrder = new SaleOrder();
		saleOrder.setSaleOrderDate(new Date());
		saleOrder.setSaleOrderDiscountTotal(receiptDto.getSaleOrderDiscountTotal());
		saleOrder.setSaleOrderTotal(receiptDto.getSaleOrderTotal());
		saleOrder = saleOrderRepository.save(saleOrder); 
		saleOrderProductService.save(saleOrderProducts, saleOrder);
		saleOrder.setSaleOrderProducts(saleOrderProducts);
		receiptDto.setSaleOrderId(saleOrder.getSaleOrderId());
		return receiptDto;
	}
}
