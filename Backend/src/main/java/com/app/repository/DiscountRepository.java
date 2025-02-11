package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.DiscountDetails;


public interface DiscountRepository extends JpaRepository<DiscountDetails, Long>{

}
