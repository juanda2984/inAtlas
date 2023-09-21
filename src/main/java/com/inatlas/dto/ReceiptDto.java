package com.inatlas.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.inatlas.entities.Product;
import com.inatlas.util.DoubleTwoDecimalSerializer;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class ReceiptDto {
	private List<ProductDto> productsDto;
	@JsonSerialize(using = DoubleTwoDecimalSerializer.class)
	private Double saleOrderTotalFull;
	@JsonSerialize(using = DoubleTwoDecimalSerializer.class)
	private Double saleOrderDiscountTotal;
	private String descriptionDiscount;
	@JsonSerialize(using = DoubleTwoDecimalSerializer.class)
	private Double saleOrderTotal;
	@JsonIgnore
	private Long saleOrderId;

	public void setProductDto(Product product) {
		if (productsDto == null) {
			productsDto = new ArrayList<>();
			productsDto.add(ProductDto.builder().productName(product.getProductName()).amount(1)
					.totalPrice(product.getProductPrice()).unitPrice(product.getProductPrice()).build());
		} else {
			
			ProductDto productDtoExist = productsDto.stream()
	                .filter(productDto -> productDto.getProductName().equals(product.getProductName()))
	                .findFirst()
	                .orElse(null);

	        if (productDtoExist != null) {
	        	productDtoExist.setAmount(productDtoExist.getAmount() + 1);
	        	productDtoExist.setTotalPrice(product.getProductPrice()+ productDtoExist.getTotalPrice());
	        } else {
	        	productsDto.add(ProductDto.builder().productName(product.getProductName()).amount(1)
						.totalPrice(product.getProductPrice()).unitPrice(product.getProductPrice()).build());
	        }
		}
	}
}
