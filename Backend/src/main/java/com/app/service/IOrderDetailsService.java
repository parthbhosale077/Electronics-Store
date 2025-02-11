package com.app.service;

import java.util.List;

import com.app.dto.CheckoutDto;
import com.app.entities.OrderDetails;


public interface IOrderDetailsService {
	
	//get all orders with ordered status
	
	List<OrderDetails> getOrdersByStatus(String status);
	
	OrderDetails updateOrderStatus(Long id, String status);
	
	List<OrderDetails> getOrdersByUser(Long userId);
	
	OrderDetails placeOrder(CheckoutDto checkout);
}
