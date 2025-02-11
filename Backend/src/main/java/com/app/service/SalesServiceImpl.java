package com.app.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dto.SalesByCategoryDTO;
import com.app.dto.SalesByProductDTO;
import com.app.repository.SalesDetailsRepository;


@Service
@Transactional
public class SalesServiceImpl implements ISalesService {
	@Autowired
	private SalesDetailsRepository salesRepo;

	@Override
	public List<SalesByProductDTO> findSalesOnProductByOrderDate(Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		List<SalesByProductDTO> result = new ArrayList<SalesByProductDTO>();
		salesRepo.findBySoldOnBetween(startDate, endDate).stream()
				.collect(Collectors.groupingBy(sd -> sd.getProduct(), //Collectors.summingDouble(sd -> sd.getTotalSale())
						Collectors.summingInt(sd -> sd.getQuantity())))
				.forEach((product, sumOfQty) -> result.add(new SalesByProductDTO(product, sumOfQty*product.getPrice(),sumOfQty )));

		return result;
	}

	@Override
	public List<SalesByCategoryDTO> findSalesOnCategoryByOrderDate(Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		List<SalesByCategoryDTO> result = new ArrayList<SalesByCategoryDTO>();
		salesRepo.findBySoldOnBetween(startDate, endDate).stream()
				.collect(Collectors.groupingBy(sd -> sd.getCategory(),
						Collectors.summingInt(sd -> sd.getQuantity())))
				.forEach((cat, sumOfQuantity) -> 
				{
//					System.out.println();
//					System.out.println("Total = " + findSalesByCategory(cat.getId(), startDate, endDate));
//					System.out.println();
					result.add(new SalesByCategoryDTO(cat,findSalesByCategory(cat.getId(), startDate, endDate),sumOfQuantity));
					
				});

		return result;
		
	}
	
	public double findSalesByCategory(long catId, Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		//double result = 0.0;
		return salesRepo.findBySoldOnBetween(startDate, endDate).stream()
		.filter(sd -> sd.getCategory().getId()==catId)
		.mapToDouble(sd -> sd.getQuantity()*sd.getProduct().getPrice())
		.sum();
				
		//return result;
	}

}
