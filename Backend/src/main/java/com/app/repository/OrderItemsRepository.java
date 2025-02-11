package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.app.entities.OrderDetails;
import com.app.entities.OrderItems;

public interface OrderItemsRepository extends JpaRepository<OrderItems, Long> {
	//@Query("select oi from OrderItems oi join fetch oi.order where ")
	//List<OrderItems> findAllOrders(long userId);
	
	List<OrderItems> findByOrder(OrderDetails orderDetails);
}
