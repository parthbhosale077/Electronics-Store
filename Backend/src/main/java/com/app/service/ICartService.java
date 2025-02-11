package com.app.service;

import java.util.List;

import com.app.dto.CartDTO;
import com.app.dto.CreatedResponse;
import com.app.entities.CartItems;

public interface ICartService {
	CreatedResponse addProductToCart(CartDTO cart);

	List<CartItems> viewProductsFromCart(Long userId);

	CreatedResponse incrementProductQuantityInCart(Long cartId, int quantity) ;
	
	CreatedResponse deleteProductFromCart(Long cartId) ;
}
