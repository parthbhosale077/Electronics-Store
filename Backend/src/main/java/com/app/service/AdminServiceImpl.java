package com.app.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dto.CreatedResponse;
import com.app.entities.DiscountDetails;
import com.app.entities.UserEntity;
import com.app.repository.DiscountRepository;
import com.app.repository.UserRepository;

@Service
@Transactional
public class AdminServiceImpl implements IAdminService {

	
	@Autowired
	private UserRepository userRepo;

	@Autowired
	private DiscountRepository discountRepo;
	
	@Override
	public List<UserEntity> getAllUsers() {
		// TODO Auto-generated method stub
		System.out.println("in service class");
		return userRepo.findUserRoles();
	}
	
	@Override
	public List<DiscountDetails> getAllDiscounts() {		
		System.out.println("in service class");
		return discountRepo.findAll();
	}
	
	@Override
	public CreatedResponse insertDiscount(DiscountDetails discountdetails) {
		// TODO Auto-generated method stub
		discountdetails.setCreatedAt(Date.valueOf(LocalDate.now()));
		System.out.println("in service class");
		 discountRepo.save(discountdetails);
		 return new CreatedResponse("success");
		 
	}
	@Override
	public DiscountDetails updatediscountDetails(DiscountDetails discountdetails) {
		discountRepo.findById(discountdetails.getId());
		
		return discountRepo.save(discountdetails);
	}
	
	@Override
	public CreatedResponse deleteDiscountDetails(long userId) {
		
		
		if (discountRepo.existsById(userId)) {
			discountRepo.deleteById(userId);
			
		}
		return new CreatedResponse("success");

	}
	@Override
	public CreatedResponse deleteUser(long userId) {

		
		if (userRepo.existsById(userId)) {
			userRepo.deleteById(userId);

		}
		return new CreatedResponse("success");

	}
}
