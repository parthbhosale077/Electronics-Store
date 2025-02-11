package com.app.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dto.CartDTO;
import com.app.dto.CreatedResponse;
import com.app.entities.CartItems;
import com.app.entities.ProductDetails;
import com.app.entities.UserEntity;
import com.app.repository.CartRepository;
import com.app.repository.ProductRepository;
import com.app.repository.UserRepository;


@Service
@Transactional
public class CartServiceImpl implements ICartService {

	@Autowired
	private CartRepository cartRepo;;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private ProductRepository productRepo;
	

	@Override
	public CreatedResponse addProductToCart(CartDTO cart) 
	{
		// TODO Auto-generated method stub
		CartItems transientCartItem = new CartItems();
		UserEntity user = userRepo.findById(cart.getUserId()).orElseThrow(() -> new RuntimeException("User Not Found!"));
		ProductDetails product = productRepo.findById(cart.getProductId()).orElseThrow(() -> new RuntimeException("Product Not Found!"));
		transientCartItem.setUser(user);
		transientCartItem.setProduct(product);
		
		int qty = (cart.getQuantity()==0 ? 1 : cart.getQuantity());
		transientCartItem.setQuantity(qty);
		
		cartRepo.save(transientCartItem);
		return new CreatedResponse("success" );
	}


	@Override
	public List<CartItems> viewProductsFromCart(Long userId) {
		// TODO Auto-generated method stub
		UserEntity user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
		return cartRepo.findByUser(user);
	}


	@Override
	public CreatedResponse deleteProductFromCart(Long cartId) {
		// TODO Auto-generated method stub
		CartItems cartItem = cartRepo.findById(cartId).orElseThrow(() -> new RuntimeException("Cart item not found"));
		cartRepo.delete(cartItem);
		return new CreatedResponse("success" );
	}


	@Override
	public CreatedResponse incrementProductQuantityInCart(Long cartId, int quantity) {
		// TODO Auto-generated method stub
		CartItems cartItem = cartRepo.findById(cartId).orElseThrow(() -> new RuntimeException("Cart item not found"));
		cartItem.setQuantity(quantity);
		cartRepo.save(cartItem);
		return new CreatedResponse("success");
	}

	
}
