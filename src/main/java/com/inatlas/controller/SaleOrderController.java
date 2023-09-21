package com.inatlas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inatlas.dto.ReceiptDto;
import com.inatlas.entities.SaleOrder;
import com.inatlas.entities.SaleOrderProduct;
import com.inatlas.service.SaleOrderService;

@RestController
@RequestMapping("/saleOrder")
public class SaleOrderController {
	private final SaleOrderService saleOrderService;

	@Autowired
	public SaleOrderController(SaleOrderService saleOrderService) {
		this.saleOrderService = saleOrderService;
	}

	@GetMapping("/")
	public ResponseEntity<List<SaleOrder>> getSaleOrderAll() {
		try {
			List<SaleOrder> saleOrders = saleOrderService.getSaleOrderAll();
			if (saleOrders.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(saleOrders, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/")
	public ResponseEntity<ReceiptDto> saveSaleOrder(@RequestBody List<SaleOrderProduct> saleOrderProducts) {
		try {
			ReceiptDto receiptDto = saleOrderService.saveSaleOrder(saleOrderProducts);
			if (receiptDto!=null) {
				return new ResponseEntity<>(receiptDto, HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
