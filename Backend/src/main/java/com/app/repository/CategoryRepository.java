package com.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.ProductCategory;

public interface CategoryRepository extends JpaRepository<ProductCategory, Long> {
	Optional<ProductCategory> findByName(String name);
}
