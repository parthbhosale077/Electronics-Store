package com.app.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;


import com.app.entities.CartItems;
import com.app.entities.ProductDetails;
import com.app.entities.UserEntity;

public interface CartRepository extends JpaRepository<CartItems, Long> {

	List<CartItems> findByUser(UserEntity user);
	
	CartItems findByUserAndProduct(UserEntity user, ProductDetails product);
}
