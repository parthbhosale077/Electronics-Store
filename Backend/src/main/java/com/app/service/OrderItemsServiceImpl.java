package com.app.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dto.OrderItemsDTO;
import com.app.entities.OrderDetails;
import com.app.entities.OrderItems;
import com.app.repository.OrderDetailsRepository;
import com.app.repository.OrderItemsRepository;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OrderItemsServiceImpl implements IOrderItemsService {

	@Autowired
	private OrderItemsRepository orderItemsRepo;
	
	@Autowired
	private OrderDetailsRepository orderDetailsRepo;
	
	
	@Override
	public List<OrderItemsDTO> getOrderItems(long orderDetailsId) {
		// TODO Auto-generated method stub
		OrderDetails od = orderDetailsRepo.findById(orderDetailsId).orElseThrow(() -> new RuntimeException("Order details not found !"));
		List<OrderItems> orderItemsList = orderItemsRepo.findByOrder(od);
		List<OrderItemsDTO> result = new ArrayList<>();
		orderItemsList.forEach( item -> result.add(new OrderItemsDTO(item.getProduct(), item.getQuantity())));
		return 	result;
		
	}
}
