package com.inatlas.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
