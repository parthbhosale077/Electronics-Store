package com.app.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ApiResponse;
import com.app.dto.CartDTO;
import com.app.dto.CheckoutDto;
import com.app.dto.DataResponse;
import com.app.dto.EditResponse;
import com.app.dto.ErrorResponse;
import com.app.dto.OrderDetailsDto;
import com.app.dto.ReviewDTO;
import com.app.dto.UserAddressDTO;
import com.app.dto.WishListDTO;
import com.app.entities.ProductDetails;
import com.app.entities.ReviewDetails;
import com.app.entities.UserEntity;
import com.app.service.ICartService;
import com.app.service.IOrderDetailsService;
import com.app.service.IOrderItemsService;
import com.app.service.IProductService;
import com.app.service.IUserService;
import com.app.service.IWishlistService;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {

	@Autowired
	private ICartService cartService;
	// private UserController userService;
	@Autowired
	private IUserService userService;

	@Autowired
	private IOrderDetailsService orderDetailsService;

	@Autowired
	private IProductService productService;

	@Autowired
	private IWishlistService wishlistService;

	@Autowired
	private IOrderItemsService orderItemsService;

	@PostMapping("/addToCart")
	public ResponseEntity<?> addToCart(@RequestBody CartDTO cart) throws IOException {
		 System.out.println("Inside addToCart : " + cart);
		try {
			return ResponseEntity.status(HttpStatus.CREATED).
					body(cartService.addProductToCart(cart));
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ErrorResponse("Something went wrong while adding product to cart", e.toString()));
		}

	}

	@GetMapping("/viewCart/{userId}")
	public ResponseEntity<?> viewCart(@PathVariable long userId) throws IOException {

		try {
			System.out.println("in view cart ");
			return ResponseEntity.status(HttpStatus.OK).
					body(new DataResponse("success", cartService.viewProductsFromCart(userId)));
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ErrorResponse("Something went wrong while adding product to cart", e.toString()));
		}
	}

	@DeleteMapping("/deleteProductFromCart/{cartId}")
	public ResponseEntity<?> deleteProductFromCart(@PathVariable Long cartId) throws IOException {

		try {
			System.out.println("in view cart " + cartId);
			return ResponseEntity.status(HttpStatus.OK).
					body(cartService.deleteProductFromCart(cartId));
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ErrorResponse("Something went wrong while removing product from cart", e.toString()));
		}

	}

	
	
	@PutMapping("/orders/updateStatus")
	public ResponseEntity<?> cancelOrder(@RequestBody OrderDetailsDto orderDto) {

		try {
			return ResponseEntity.status(HttpStatus.OK).
					body(new EditResponse("success",orderDetailsService.updateOrderStatus(orderDto.getId(), "CANCELLED")));

			// o.s.http.ResponseEntity(T body,HttpStatus stsCode)
//			return new ResponseEntity<>(orderDetailsService.updateOrderStatus(orderDto.getId(), "CANCELLED"),
//					HttpStatus.OK);
		} catch (RuntimeException e) {
			System.out.println("err in user controller " + e);
			return new ResponseEntity<>(new ApiResponse(e.getMessage()), HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping("/viewOrderHistory/{userId}")
	public ResponseEntity<?> viewOrderHistory(@PathVariable long userId) throws IOException {

		try {
			System.out.println("in view all orders " + userId);
			return ResponseEntity.status(HttpStatus.OK).
					body(new DataResponse("success", orderDetailsService.getOrdersByUser(userId)));
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ErrorResponse("Something went wrong while fetching order history", e.toString()));
		}
	}

	@PostMapping("/addToWishlist")
	public ResponseEntity<?> addToWishlist(@RequestBody WishListDTO wishlist) throws IOException {

		try {
			return ResponseEntity.status(HttpStatus.CREATED).
					body(wishlistService.addProductToWishlist(wishlist));
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ErrorResponse("Something went wrong while adding product to wishlist", e.toString()));
		}

	}

	@GetMapping("/viewWishlist/{userId}")
	public ResponseEntity<?> viewWishlist(@PathVariable long userId) throws IOException {

		try {
			System.out.println("in view all orders " + userId);
			List<ProductDetails> userWishlist = wishlistService.getUserWishlist(userId);
//			if (userWishlist == null)
//				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new DataResponse("success",new List<ProductDetails>()));
			return ResponseEntity.status(HttpStatus.OK).
					body(new DataResponse("success",userWishlist));
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ErrorResponse("Something went wrong fetching wishlist ", e.toString()));
		}
	}

	@DeleteMapping("/wishlist/remove/{userId}/{productId}")
	public ResponseEntity<?> removeFromWishlist(@PathVariable long userId, @PathVariable long productId) throws IOException {

		try {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new EditResponse("success", wishlistService.removeProductFromWishlist(userId, productId)));

		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ErrorResponse("Something went wrong removing product from wishlist ", e.toString()));
		}

	}

	@PutMapping("/cart/incrementProductQuantity/{cartId}/{quantity}")
	public ResponseEntity<?> incrementProductQuantity(@PathVariable long cartId, @PathVariable int quantity) throws IOException {
		try {
			return ResponseEntity.status(HttpStatus.OK).
					body(cartService.incrementProductQuantityInCart(cartId, quantity));
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ErrorResponse("Something went wrong while increasing product quantity", e.toString()));
		}

	}

	@GetMapping("/getOrderDetails/{orderDetailsId}")
	public ResponseEntity<?> getOrderDetails(@PathVariable long orderDetailsId) throws IOException {
		try {
			return ResponseEntity.status(HttpStatus.OK).
					body(new DataResponse("success",orderItemsService.getOrderItems(orderDetailsId)));

		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ErrorResponse("Something went wrong while fetching order details", e.toString()));
		}

	}

	
	@PostMapping("/addProductReview")
	public ResponseEntity<?> addBookReview(@RequestBody ReviewDTO review) {
		
		try {
			return ResponseEntity.status(HttpStatus.CREATED).
					body(productService.addProductReview(review));
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ErrorResponse("Something went wrong while adding review", e.toString()));
		}
	}

	@PostMapping("/addNewAddress")
	public ResponseEntity<?> addNewAddress(@RequestBody UserAddressDTO address) {
		System.out.println("add new address" + address);
		try {
			return ResponseEntity.status(HttpStatus.OK).body(userService.insertAddress(address));
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ErrorResponse("Something went wrong while getting address", e.toString()));
		}
		
	}
	
	@GetMapping("/getAddress/{userId}")
	public ResponseEntity<?> getAddress(@PathVariable Long userId){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(new DataResponse("success", userService.getUserAddress(userId)));
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ErrorResponse("Something went wrong while getting address", e.toString()));
		}
	}
	

//	@PutMapping("/updateAccountDetails/{userId}")
//	public UserEntity updateaccountDetails(@PathVariable(value = "userId") Long userId,
//			@RequestBody UserEntity account) {
//		System.out.println(" update  account details" + account);
//		return userService.updateAccountDetails(userId, account);
//	}
	
	
	@PutMapping("/updateAccountDetails/{userId}")
	public ResponseEntity<?>  updateaccountDetails(@PathVariable(value = "userId") Long userId,
			@RequestBody UserEntity account) throws IOException
	{
		
		try {
			return ResponseEntity.status(HttpStatus.OK).body(userService.updateAccountDetails(userId, account));
		
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ErrorResponse("Something went wrong while increasing product quantity", e.toString()));
	}
		}

	
	@PostMapping("/checkout")
	public ResponseEntity<?> checkout(@RequestBody CheckoutDto checkout) throws IOException {

		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(new EditResponse("success", orderDetailsService.placeOrder(checkout)));
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ErrorResponse("Something went wrong while adding book to wishlist", e.toString()));
		}

	}
	
	@PutMapping("/editAddress/{addressId}")
	public ResponseEntity<?> editAddress(@RequestBody UserAddressDTO address, @PathVariable Long addressId) {
		System.out.println("add new address" + address);
		try {
			return ResponseEntity.status(HttpStatus.OK).body(userService.editAddress(address, addressId));
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ErrorResponse("Something went wrong while updating address", e.toString()));
		}
		
	}
	@GetMapping("/getReviews/{productId}")
	public List<ReviewDetails> getReviews(@PathVariable Long productId) 
	{
		List<ReviewDetails> list=productService.getReviews(productId);
		System.out.println(list);
		return  list;

	}

}


