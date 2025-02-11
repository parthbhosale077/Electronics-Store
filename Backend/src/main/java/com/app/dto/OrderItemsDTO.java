package com.app.dto;

import com.app.entities.ProductDetails;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;



@Getter
@Setter
@ToString
@AllArgsConstructor


public class OrderItemsDTO
{	private ProductDetails product;
	private int quantity;	
}
