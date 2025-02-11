package com.app.dto;

import com.app.entities.ProductCategory;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SalesByCategoryDTO {
	
	public SalesByCategoryDTO(ProductCategory cat, Double sum,  int quantity) {
		
		category = cat;
		totalSale = sum;
		this.quantity = quantity;
	}
	private ProductCategory category;
	private double totalSale;
	private int quantity;
}
