package com.app.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.repository.DiscountRepository;

@Service
//@Transactional
public class DiscountServiceImpl implements IDiscountService {

	@SuppressWarnings("unused")
	@Autowired
	private DiscountRepository discountRepo;
	
	
	
}
