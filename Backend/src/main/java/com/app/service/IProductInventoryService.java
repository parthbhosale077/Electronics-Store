package com.app.service;

import java.util.List;


import com.app.entities.ProductInventory;

public interface IProductInventoryService {

	List<ProductInventory> getAllProductsFromInventory();
	
	ProductInventory updateInventoryStock(Long id, double qty);
}
