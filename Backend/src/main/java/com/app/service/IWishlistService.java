package com.app.service;

import java.util.List;

import com.app.dto.CreatedResponse;
import com.app.dto.WishListDTO;

import com.app.entities.ProductDetails;


public interface IWishlistService {
	CreatedResponse addProductToWishlist(WishListDTO wishlist);

	List<ProductDetails> getUserWishlist(long userId);
	
	CreatedResponse removeProductFromWishlist(long userId, long bookId);


}
