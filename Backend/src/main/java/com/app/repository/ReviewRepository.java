package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.entities.ReviewDetails;

public interface ReviewRepository extends JpaRepository<ReviewDetails, Long> {
	
	@Query("select review from ReviewDetails review where review.product.id=?1")
	List<ReviewDetails> findByProductId(Long productId);
	
}
