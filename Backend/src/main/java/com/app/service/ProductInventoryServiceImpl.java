package com.app.service;


import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.entities.ProductInventory;
import com.app.repository.ProductInventoryRepository;
import java.util.List;


@Service
@Transactional
public class ProductInventoryServiceImpl implements IProductInventoryService{

	@Autowired
	private ProductInventoryRepository productInventoryRepo;
	
	@Override
	public ProductInventory updateInventoryStock(Long id, double qty) {
		// TODO Auto-generated method stub
		//System.out.println("IN SERVICE");
		ProductInventory b = productInventoryRepo.findById(id).orElseThrow();
		b.setQuantity(qty);
		return productInventoryRepo.save(b);
	}



	@Override
	public List<ProductInventory> getAllProductsFromInventory() {
		// TODO Auto-generated method stub
		return productInventoryRepo.findAll();
	}

	
	
}
