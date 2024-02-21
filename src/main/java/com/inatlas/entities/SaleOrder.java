package com.inatlas.entities;

import java.util.Date;
import java.util.List;


import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Data
@NoArgsConstructor
public class SaleOrder {
	@Id
	@GeneratedValue
	private Long saleOrderId;	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
	private Date saleOrderDate;
	private Double saleOrderDiscountTotal;
	private Double saleOrderTotal;
	@OneToMany(mappedBy = "saleOrder")
    private List<SaleOrderProduct> saleOrderProducts;
}
