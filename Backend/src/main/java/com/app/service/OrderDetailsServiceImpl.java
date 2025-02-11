package com.app.service;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dto.CheckoutDto;


import com.app.entities.ProductDetails;
import com.app.entities.ProductInventory;
import com.app.entities.CartItems;
import com.app.entities.OrderDetails;
import com.app.entities.OrderItems;
import com.app.entities.SalesDetails;
import com.app.entities.UserAddress;
import com.app.entities.UserEntity;
import com.app.repository.AddressRepository;
import com.app.repository.ProductInventoryRepository;
import com.app.repository.ProductRepository;
import com.app.repository.CartRepository;
import com.app.repository.OrderDetailsRepository;
import com.app.repository.OrderItemsRepository;
import com.app.repository.SalesDetailsRepository;
import com.app.repository.UserRepository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.modelmapper.ModelMapper;

@Service
@Transactional
public class OrderDetailsServiceImpl implements IOrderDetailsService {

	@Autowired
	private OrderDetailsRepository orderDetailsRepo;
	
	@Autowired
	private OrderItemsRepository orderItemsRepo;
	
	@Autowired
	private ProductInventoryRepository productInventoryRepo;
	
	@Autowired
	private ProductRepository productRepo;
	
	@Autowired
	private AddressRepository addressRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private SalesDetailsRepository salesRepo;
	
	@SuppressWarnings("unused")
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private CartRepository cartRepo;

	@Override
	public List<OrderDetails> getOrdersByStatus(String status) {
		// TODO Auto-generated method stub
		System.out.println("SERVICE : "+status);
		//List<OrderDetails> orderList = 
		return orderDetailsRepo.findByStatus(status);
	}

	@Override
	public OrderDetails updateOrderStatus(Long id, String status) {
		// TODO Auto-generated method stub
		
		OrderDetails od = orderDetailsRepo.findById(id).orElseThrow();
		
		od.setStatus(status);
		od.setStatusUpdatedAt(Date.valueOf(LocalDate.now()));
		return od;
	}

	@Override
	public List<OrderDetails> getOrdersByUser(Long userId) {
		// TODO Auto-generated method stub
		UserEntity user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
		System.out.println("IN service impl "+ user);
		return orderDetailsRepo.findByUser(user);
	}

	@Override
	public OrderDetails placeOrder(CheckoutDto checkout) {
		// TODO Auto-generated method stub
		UserEntity user = userRepo.findById(checkout.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
		UserAddress address = addressRepo.findById(checkout.getAddressId()).orElseThrow(() -> new RuntimeException("Address not found"));
//		OrderDetails orderDetails = new OrderDetails();
//		orderDetails.setUser(user);
//		orderDetails.setAddress(address);
//		orderDetails.setCodStatus(checkout.isCodStatus());
//		orderDetails.setStatus("ORDERED");
//		orderDetails.setCreatedAt(Date.valueOf(LocalDate.now()));
//		orderDetails.setStatusUpdatedAt(Date.valueOf(LocalDate.now()));
		OrderDetails persistentOrder = orderDetailsRepo
										.save(new OrderDetails(user, Date.valueOf(LocalDate.now()),"ORDERED", address, checkout.isCodStatus(), Date.valueOf(LocalDate.now())));
		
		checkout.getProducts().forEach((product) ->{
			System.out.println("Product ORDERED : "+ product);
			ProductDetails b =  productRepo.findById(product.getProductId()).orElseThrow(() -> new RuntimeException("Product not found"));
			orderItemsRepo.save(new OrderItems(persistentOrder,b,product.getQuantity()));
			ProductInventory productInv =  productInventoryRepo.findByProduct(b);
			productInv.setQuantity(productInv.getQuantity() - product.getQuantity());
		
			//adding details to salesDetails table
			
			//salesRepo.save(new SalesDetails(b,b.getCategory(),b.getPrice()*product.getQuantity(), Date.valueOf(LocalDate.now()), product.getQuantity() ));
			salesRepo.save(new SalesDetails(b,b.getCategory(), Date.valueOf(LocalDate.now()), product.getQuantity() ));
		});
		
		List<CartItems> cartItems =  cartRepo.findByUser(user);
		cartItems.forEach((cartItem) ->{
			cartRepo.delete(cartItem);
		});
		
		return persistentOrder;
	}

	
	
}
