package com.app.service;

import java.util.List;

import com.app.dto.OrderItemsDTO;

public interface IOrderItemsService {
	
	List<OrderItemsDTO> getOrderItems(long orderDetailsId);

}
