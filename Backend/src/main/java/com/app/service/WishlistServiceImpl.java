package com.app.service;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dto.CreatedResponse;
import com.app.dto.WishListDTO;
import com.app.entities.ProductDetails;
import com.app.entities.UserEntity;
import com.app.entities.Wishlist;
import com.app.repository.ProductRepository;
import com.app.repository.UserRepository;
import com.app.repository.WishlistRepository;

@Service
@Transactional
public class WishlistServiceImpl implements IWishlistService {

	@Autowired
	private WishlistRepository wishlistRepo;;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private ProductRepository productRepo;

	@Override
	public CreatedResponse addProductToWishlist(WishListDTO wishlist) {
		// TODO Auto-generated method stub
		ProductDetails product = productRepo.findById(wishlist.getProductId()).orElseThrow(() -> new RuntimeException("product Not Found!"));
		UserEntity user = userRepo.findById(wishlist.getUserId()).orElseThrow(() -> new RuntimeException("User Not Found!"));
		Wishlist existingWishList = wishlistRepo.findByUser(user);
		
		if(existingWishList != null)
		{		
			System.out.println("In IF");
			existingWishList.addProduct(product);
			wishlistRepo.save(existingWishList);
		}
		else {
			System.out.println("In ELSE");
			Wishlist wishList = new Wishlist();
			wishList.setUser(user);
			wishList.addProduct(product);
			wishlistRepo.save(wishList);
		}
		
		return new CreatedResponse("Product Added to wishlist successfully " );
	}


	@Override
	public List<ProductDetails> getUserWishlist(long userId) {
		// TODO Auto-generated method stub
		UserEntity user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User Not Found!"));

		System.out.println("USER" + user);
		System.out.println("WISHLIST" + wishlistRepo.findByUser(user));
		if(wishlistRepo.findByUser(user)==null)
			return new ArrayList<ProductDetails>();
		List<ProductDetails> products = wishlistRepo.findByUser(user).getProducts().stream().collect(Collectors.toList());
		return products;
	}


	@Override
	public CreatedResponse removeProductFromWishlist(long userId, long productId) {
		// TODO Auto-generated method stub
		ProductDetails product= productRepo.findById(productId).orElseThrow(() -> new RuntimeException("product Not Found!"));
		UserEntity user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User Not Found!"));
		Wishlist existingWishList = wishlistRepo.findByUser(user);
		//System.out.println("WISHLIST" + existingWishList);
		existingWishList.removeProduct(product);
		wishlistRepo.save(existingWishList);
		return new CreatedResponse("Product removed from wishlist successfully " );

	}

	
}
