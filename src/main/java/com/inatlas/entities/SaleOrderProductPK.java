package com.inatlas.entities;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaleOrderProductPK implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long product;
	private Long saleOrder;

}
