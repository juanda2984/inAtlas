package com.inatlas.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.inatlas.util.DoubleTwoDecimalSerializer;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class ProductDto {
	private Integer amount;
	private String productName;
	private Double unitPrice;
	@JsonSerialize(using = DoubleTwoDecimalSerializer.class)
	private Double totalPrice;
}
