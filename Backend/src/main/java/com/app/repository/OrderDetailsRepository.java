package com.app.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.app.entities.OrderDetails;

import com.app.entities.UserEntity;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long> {
	
	@Query("select od from OrderDetails od join fetch od.user where od.status=?1 ")
	//@Query("select od.id, od.createdAt, od.status from OrderDetails od where od.status=?1 ")
	List<OrderDetails> findByStatus(String status);
	
	List<OrderDetails> findByUser(UserEntity user);
}
