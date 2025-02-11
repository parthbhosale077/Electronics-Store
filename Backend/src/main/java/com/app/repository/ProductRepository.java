package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.entities.ProductDetails;

public interface ProductRepository extends JpaRepository<ProductDetails, Long> {
	
	@Query("select product from ProductDetails product"
			+ " join fetch product.category where lower(product.category.name)"
			+ " like lower(concat('%', ?1,'%')) and product.removedStatus=0")
	List<ProductDetails> findByCategoryName(String categoryName);
	
	@Query("select product from ProductDetails product"
			+ " join fetch product.category where lower(product.name)"
			+ " like lower(concat('%', ?1,'%')) and product.removedStatus=0")
	List<ProductDetails> findByName(String name);
	
	@Query("select product from ProductDetails product"
			+ " join fetch product.category where lower(product.brand)"
			+ " like lower(concat('%', ?1,'%')) and product.removedStatus=0")
	List<ProductDetails> findByBrand(String brand);
	
	List<ProductDetails> findByRemovedStatus(boolean status);
}
