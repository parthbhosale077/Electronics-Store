package com.app.service;

import java.util.List;

import com.app.dto.ApiResponse;
import com.app.dto.CreatedResponse;
import com.app.dto.ProductDto;
import com.app.dto.ReviewDTO;
import com.app.entities.ProductDetails;
import com.app.entities.ReviewDetails;

public interface IProductService {
	
	CreatedResponse addProductDetails(ProductDto product);
	
	List<ProductDetails> getAllProducts();
	
	ApiResponse deleteProduct(Long productId);
	
	ProductDetails getProductById(Long productId);
	
	List<ProductDetails> getProductByCategory(String category);
	
	List<ProductDetails> findProductByName(String name);
	
	List<ProductDetails> findProductByBrand(String brand);
	
	CreatedResponse editProductDetails(ProductDto newProduct, Long pid);

	ApiResponse addProductReview(ReviewDTO reviewDetails);
	
	List<ReviewDetails> getReviews(Long productId);
	
	CreatedResponse removeProduct(Long productId);
	
	List<ProductDetails> getRemovedProducts();
	
	CreatedResponse makeProductAvailable(Long productId);
}
