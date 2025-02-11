package com.app.service;

import java.util.List;

import com.app.dto.ApiResponse;
import com.app.dto.CategoryDTO;
import com.app.entities.ProductCategory;

public interface ICategoryService {
	ApiResponse addCategory(CategoryDTO category);
	
	List<ProductCategory> getCategories();
	
}
