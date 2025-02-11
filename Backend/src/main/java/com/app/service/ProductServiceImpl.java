package com.app.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dto.ApiResponse;
import com.app.dto.CreatedResponse;
import com.app.dto.ProductDto;
import com.app.dto.ReviewDTO;
import com.app.entities.ProductCategory;
import com.app.entities.ProductDetails;
import com.app.entities.ProductInventory;
import com.app.entities.ReviewDetails;
import com.app.entities.UserEntity;
import com.app.repository.CategoryRepository;
import com.app.repository.ProductInventoryRepository;
import com.app.repository.ProductRepository;
import com.app.repository.ReviewRepository;
import com.app.repository.UserRepository;

@Service
@Transactional
public class ProductServiceImpl implements IProductService {

	@Autowired
	private ProductRepository productRepo;
	
	@Autowired
	private ReviewRepository reviewRepo;

	@Autowired
	private CategoryRepository catRepo;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private UserRepository userRepo;

	@Autowired
	private ProductInventoryRepository ProductInventoryRepo;


	@Override
	public CreatedResponse addProductDetails(ProductDto product) {
		ProductDetails productDetails = mapper.map(product, ProductDetails.class);
		ProductCategory category = catRepo.findByName(product.getCategory().getName()).orElseThrow(() -> new RuntimeException("Category Not Found!"));
		productDetails.setCategory(category);
		productDetails.setRemovedStatus(false);
		ProductDetails persistentProduct= productRepo.save(productDetails);
		
		ProductInventoryRepo.save(new ProductInventory(persistentProduct,0));
		return new CreatedResponse("success");
	}


	@Override
	public List<ProductDetails> getAllProducts(){
		//List<BookDetails> books = bookRepo.findAll();
		List<ProductDetails> products= productRepo.findByRemovedStatus(false);
		return products;
	  }


	@Override
	public ApiResponse deleteProduct(Long productId) {
		System.out.println("Inside delete product service");
		ProductDetails product= productRepo.findById(productId).orElseThrow(() -> new RuntimeException("Book Not Found!!"));
		productRepo.delete(product);
		return new ApiResponse("success");
	}
	

	@Override
	public ProductDetails getProductById(Long id) {
		// TODO Auto-generated method stub
		return productRepo.findById(id).orElseThrow();
	}


	@Override
	public List<ProductDetails> getProductByCategory(String category) {
		return productRepo.findByCategoryName(category);
	}


	@Override
	public List<ProductDetails> findProductByName(String name) {
		return productRepo.findByName(name);
	}
	
	@Override
	public List<ProductDetails> findProductByBrand(String brand){
		return productRepo.findByBrand(brand);
	}


	@Override
	public CreatedResponse editProductDetails(ProductDto newProduct, Long pid) {
		// TODO Auto-generated method stub
		ProductDetails product= productRepo.findById(pid).orElseThrow(() -> new RuntimeException("product Not Found!"));;
		//ProductCategory category = catRepo.findByName(newProduct.getCategory().getName()).orElseThrow(() -> new RuntimeException("Category Not Found!"));
		
		
		product.setImagePath(newProduct.getImagePath());
		product.setName(newProduct.getName());
		product.setPrice(newProduct.getPrice());
		product.setBrand(newProduct.getBrand());
		product.setDescription(newProduct.getDescription());
		
		productRepo.save(product);
		
		return new CreatedResponse("product details edited successfully with image path " + product.getImagePath());
	}
	
	@Override
	public ApiResponse addProductReview(ReviewDTO reviewDetails) {
		
		UserEntity user = userRepo.findById(reviewDetails.getUser()).orElseThrow(() -> new RuntimeException("User not found!"));
		ProductDetails product = productRepo.findById(reviewDetails.getProduct()).orElseThrow(() -> new RuntimeException("Product not found!"));
		ReviewDetails review = mapper.map(reviewDetails, ReviewDetails.class);
		review.setDateAdded(Date.valueOf(LocalDate.now()));
		review.setUser(user);
		review.setProduct(product);
		reviewRepo.save(review);
		return new ApiResponse("Review Added Successfully!");
	}


	@Override
	public List<ReviewDetails> getReviews(Long productId) {
		List <ReviewDetails> reviews = reviewRepo.findByProductId(productId);
		return reviews;

	}

	@Override
	public CreatedResponse removeProduct(Long productId) {
		// TODO Auto-generated method stub
		ProductDetails product = productRepo.findById(productId).orElseThrow();
		product.setRemovedStatus(true);
		productRepo.save(product);
		return new CreatedResponse("product removed successfully ");
	}


	@Override
	public List<ProductDetails> getRemovedProducts() {
		// TODO Auto-generated method stub
		return productRepo.findByRemovedStatus(true);
	}


	@Override
	public CreatedResponse makeProductAvailable(Long productId) {
		// TODO Auto-generated method stub
		ProductDetails product = productRepo.findById(productId).orElseThrow();
		product.setRemovedStatus(false);
		productRepo.save(product);
		return new CreatedResponse("product is available now ! ");
	}

	
}
