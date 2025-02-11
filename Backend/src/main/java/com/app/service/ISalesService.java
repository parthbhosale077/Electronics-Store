package com.app.service;

import java.sql.Date;
import java.util.List;

import com.app.dto.SalesByCategoryDTO;
import com.app.dto.SalesByProductDTO;


public interface ISalesService {
	
	List<SalesByProductDTO> findSalesOnProductByOrderDate(Date startDate, Date endDate);

	List<SalesByCategoryDTO> findSalesOnCategoryByOrderDate(Date startDate, Date endDate);
}
