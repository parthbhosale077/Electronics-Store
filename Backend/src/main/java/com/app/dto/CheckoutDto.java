package com.app.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CheckoutDto {

	
	List<OrderProducts> products;
	private boolean codStatus;
	private long addressId;
	private long userId;
}
