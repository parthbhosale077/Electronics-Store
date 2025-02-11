package com.app.service;

import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dto.ApiResponse;
import com.app.dto.CategoryDTO;
import com.app.entities.ProductCategory;
import com.app.repository.CategoryRepository;

@Service
@Transactional
public class CategoryServiceImpl implements ICategoryService {

	@Autowired
	private CategoryRepository catRepo;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public ApiResponse addCategory(CategoryDTO category) {
		ProductCategory categoryDetails = mapper.map(category, ProductCategory.class);
		System.out.println(category.getName());
		System.out.println(categoryDetails.getName());
		catRepo.save(categoryDetails);
		return new ApiResponse("success");
		
	}

	@Override
	public List<ProductCategory> getCategories() {
		List<ProductCategory> categories = catRepo.findAll();
		return categories;
	}
}
