package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.ProductDetails;
import com.app.entities.ProductInventory;

public interface ProductInventoryRepository extends JpaRepository<ProductInventory, Long> {
	
	ProductInventory findByProduct(ProductDetails product);
	
}
