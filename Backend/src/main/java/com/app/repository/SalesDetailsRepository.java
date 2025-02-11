package com.app.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.SalesDetails;


public interface SalesDetailsRepository extends JpaRepository<SalesDetails, Long> {
	
	 //@Query("select sd from SalesDetails sd where sd.soldOn BETWEEN :startDate AND :endDate")
	List<SalesDetails> findBySoldOnBetween(Date startDate, Date endDate );
}
