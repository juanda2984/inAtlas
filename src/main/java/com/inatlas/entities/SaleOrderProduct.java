package com.inatlas.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Data
@NoArgsConstructor
@IdClass(SaleOrderProductPK.class)
public class SaleOrderProduct {
	@Id
	@ManyToOne
	@JoinColumn(name = "SALE_ORDER_PRODUCT_PRODUCT_ID")
	private Product product;
	@Id
	@ManyToOne
    @JoinColumn(name = "SALE_ORDER_PRODUCT_SALE_ORDER_ID")
	@JsonIgnore
    private SaleOrder saleOrder;
}
