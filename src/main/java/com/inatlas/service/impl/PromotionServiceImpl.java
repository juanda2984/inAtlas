package com.inatlas.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inatlas.dto.ReceiptDto;
import com.inatlas.entities.Product;
import com.inatlas.entities.SaleOrderProduct;
import com.inatlas.service.ProductService;
import com.inatlas.service.PromotionService;

@Service
public class PromotionServiceImpl implements PromotionService {

	static final String LATTE = "Latte";
	static final double MIN_TOTAL_FOOD_AND_DRINK = 50;
	static final String ESPRESSO = "Espresso";
	static final Double DISCOUNT_PERCENTAGE = 0.05;
	static final long DISCOUNT_LATTE = 3;
	static final String FREE_ESPRESSO_PROMOTION = "You get free espresso for buying more than one latte!";
	static final String PROMOTION_MORE_EIGHT_PRODUCTS = "You have more than 8 products, we give you a 5% discount on your total purchase!";
	static final String PROMOTION_MORE_FIFTY = "Your order has food and drinks with a total value of more than $50, each Latte you have ordered is reduced to $3.";

	private final ProductService productService;

	@Autowired
	public PromotionServiceImpl(ProductService productService) {
		this.productService = productService;
	}

	@Override
	public ReceiptDto applyPromotions(List<SaleOrderProduct> saleOrderProducts) {
		ReceiptDto receiptDto = ReceiptDto.builder().build();
		calculateTotal(saleOrderProducts, receiptDto);

		Product productDiscountPromotionTwoLatte = new Product();
		double discountPromotionMoreEightProducts = 0.0;
		double discountPromotionMoreFifty = 0.0;

		if (orderContainsTwoLattes(saleOrderProducts)) {
			productDiscountPromotionTwoLatte = verifyPromotionTwoLattes();
		}

		if (orderContainsMoreEightProducts(saleOrderProducts)) {
			discountPromotionMoreEightProducts = verifyPromotionMoreEightProducts(receiptDto);
		}

		if (orderContainsMoreFifty(saleOrderProducts)) {
			discountPromotionMoreFifty = verifyPromotionMoreFifty(receiptDto, saleOrderProducts);
		}

		veryfiPromotionTotal(productDiscountPromotionTwoLatte, discountPromotionMoreEightProducts,
				discountPromotionMoreFifty, receiptDto, saleOrderProducts);

		return receiptDto;
	}

	private void calculateTotal(List<SaleOrderProduct> saleOrderProducts, ReceiptDto receiptDto) {
		double total = 0.0;
		for (SaleOrderProduct saleOrderProduct : saleOrderProducts) {
			Product product = productService.getProductByProductName(saleOrderProduct.getProduct().getProductName());
			saleOrderProduct.setProduct(product);
			receiptDto.setProductDto(product);
			total += product.getProductPrice();
		}
		receiptDto.setSaleOrderDiscountTotal(0.0);
		receiptDto.setSaleOrderTotal(total);
		receiptDto.setSaleOrderTotalFull(total);
	}

	private boolean orderContainsTwoLattes(List<SaleOrderProduct> saleOrderProducts) {
		long lattesCount = saleOrderProducts.stream()
				.filter(saleOrderProduct -> saleOrderProduct.getProduct().getProductName().equalsIgnoreCase(LATTE))
				.count();
		return lattesCount >= 2;
	}

	private Product verifyPromotionTwoLattes() {
		return productService.getProductByProductName(ESPRESSO);
	}

	private void applyPromotionTwoLattes(ReceiptDto receiptDto, List<SaleOrderProduct> saleOrderProducts,
			Product productDiscountPromotionTwoLatte, String descriptionDiscount) {
		SaleOrderProduct saleOrderProduct = new SaleOrderProduct();
		saleOrderProduct.setProduct(productDiscountPromotionTwoLatte);
		saleOrderProducts.add(saleOrderProduct);
		receiptDto.setProductDto(productDiscountPromotionTwoLatte);
		receiptDto.setSaleOrderTotal(receiptDto.getSaleOrderTotal() - saleOrderProduct.getProduct().getProductPrice());
		receiptDto.setSaleOrderDiscountTotal(
				receiptDto.getSaleOrderDiscountTotal() + saleOrderProduct.getProduct().getProductPrice());
		receiptDto.setDescriptionDiscount(descriptionDiscount);
	}

	private boolean orderContainsMoreEightProducts(List<SaleOrderProduct> saleOrderProducts) {
		return saleOrderProducts.size() > 8;
	}

	private double verifyPromotionMoreEightProducts(ReceiptDto receiptDto) {
		return receiptDto.getSaleOrderTotal() * DISCOUNT_PERCENTAGE;
	}

	private boolean orderContainsMoreFifty(List<SaleOrderProduct> saleOrderProducts) {
		double totalComidaBebida = saleOrderProducts.stream()
				.mapToDouble(saleOrderProduct -> saleOrderProduct.getProduct().getProductPrice()).sum();
		return totalComidaBebida > MIN_TOTAL_FOOD_AND_DRINK;
	}

	private double verifyPromotionMoreFifty(ReceiptDto receiptDto, List<SaleOrderProduct> saleOrderProducts) {
		long lattesCount = saleOrderProducts.stream()
				.filter(saleOrderProduct -> saleOrderProduct.getProduct().getProductName().equalsIgnoreCase(LATTE))
				.count();
		;

		double totalPromotionDiscountLocal = (lattesCount
				* productService.getProductByProductName(LATTE).getProductPrice()) - (lattesCount * DISCOUNT_LATTE);
		return totalPromotionDiscountLocal;
	}

	private void applyPromotionGeneric(ReceiptDto receiptDto, double discountPromotionMoreFifty,
			String descriptionDiscount) {
		receiptDto.setSaleOrderTotal(receiptDto.getSaleOrderTotal() - discountPromotionMoreFifty);
		receiptDto.setSaleOrderDiscountTotal(receiptDto.getSaleOrderDiscountTotal() + discountPromotionMoreFifty);
		receiptDto.setDescriptionDiscount(descriptionDiscount);
	}

	private void veryfiPromotionTotal(Product productDiscountPromotionTwoLatte,
			double discountPromotionMoreEightProducts, double discountPromotionMoreFifty, ReceiptDto receiptDto,
			List<SaleOrderProduct> saleOrderProducts) {
		double max = Math.max(
				Math.max(productDiscountPromotionTwoLatte.getProductPrice(), discountPromotionMoreEightProducts),
				discountPromotionMoreFifty);
		if (max > 0) {
			if (max == productDiscountPromotionTwoLatte.getProductPrice()) {
				applyPromotionTwoLattes(receiptDto, saleOrderProducts, productDiscountPromotionTwoLatte,
						FREE_ESPRESSO_PROMOTION);
			} else if (max == discountPromotionMoreEightProducts) {
				applyPromotionGeneric(receiptDto, discountPromotionMoreEightProducts, PROMOTION_MORE_EIGHT_PRODUCTS);
			} else {
				applyPromotionGeneric(receiptDto, discountPromotionMoreFifty, PROMOTION_MORE_FIFTY);
			}
		}
	}
}
