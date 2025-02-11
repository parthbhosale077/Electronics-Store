package com.app.service;

import java.util.List;

import com.app.dto.CreatedResponse;
import com.app.entities.DiscountDetails;
import com.app.entities.UserEntity;

public interface IAdminService {
	
	public List<UserEntity> getAllUsers();
	public List<DiscountDetails> getAllDiscounts();
	//public DiscountDetails insertDiscount(DiscountDetails discount);
	public DiscountDetails updatediscountDetails(DiscountDetails discount);
	
	CreatedResponse deleteUser(long userId);
	CreatedResponse deleteDiscountDetails(long userId);
	CreatedResponse insertDiscount(DiscountDetails discount);
}
