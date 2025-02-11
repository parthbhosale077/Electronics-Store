package com.app.dto;

import com.app.entities.ProductDetails;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SalesByProductDTO {
	
	public SalesByProductDTO(ProductDetails product, Double sum, int quantity) {
		// TODO Auto-generated constructor stub
		this.product =  product;
		this.totalSale = sum;
		this.quantity = quantity;
	}
	private ProductDetails product;
	private double totalSale;
	private int quantity;
}
