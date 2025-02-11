package com.app.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.DataResponse;
import com.app.dto.EditResponse;
import com.app.dto.ErrorResponse;
import com.app.entities.ProductDetails;
import com.app.service.ICategoryService;
import com.app.service.IProductService;
import com.app.service.ImageHandlingService;

@RestController
@RequestMapping("/home")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class HomeController {
	
	@Autowired
	private IProductService productService;
	
	@Autowired
	private ImageHandlingService imageService;
	
	@Autowired
	private ICategoryService categoryService;

	@GetMapping(value="/{productId}/images",produces = 
		{MediaType.IMAGE_GIF_VALUE,MediaType.IMAGE_JPEG_VALUE,MediaType.IMAGE_PNG_VALUE})
	public ResponseEntity<?> downloadImage(@PathVariable long productId) throws IOException {
		return ResponseEntity.ok(imageService.restoreImage(productId));
	}
	
	@GetMapping("/getCategories")
	public ResponseEntity<?> getCategories(){
		return ResponseEntity.
				ok(new DataResponse("success", categoryService.getCategories()));
	}
	
	
//	@GetMapping("/view")
//	public ResponseEntity<?> getProductByCategory(@RequestParam String category) {
////		System.out.println(category.getName());
//		return ResponseEntity.status(HttpStatus.OK).
//				body(new DataResponse("success", productService.getProductByCategory(category)));
//	}
	
	@GetMapping("/view/{category}")
	public ResponseEntity<?> getProductByCategory(@PathVariable String category) {
//		System.out.println(category.getName());
		return ResponseEntity.status(HttpStatus.OK).
				body(new DataResponse("success", productService.getProductByCategory(category)));
	}

	@GetMapping("/search/name")
	public ResponseEntity<?> getProductByName(@RequestParam String name) {
		try {
			return ResponseEntity.status(HttpStatus.OK).
					body(new DataResponse("success", productService.findProductByName(name)));
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ErrorResponse("Category addition failed", e.toString()));
		}
	}
	
	@GetMapping("/search/brand")
	public ResponseEntity<?> getProductByBrand(@RequestParam String brand) {
		try {
			return ResponseEntity.status(HttpStatus.OK).
					body(new DataResponse("success", productService.findProductByBrand(brand)));
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ErrorResponse("Category addition failed", e.toString()));
		}
	}
	
	@GetMapping("/getReviews/{productId}")
	public ResponseEntity<?> getReviews(@PathVariable Long productId) {
		try {
			return ResponseEntity.status(HttpStatus.OK).
					body(new DataResponse("success", productService.getReviews(productId)));
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ErrorResponse("Something went wrong while getting reviews", e.toString()));
		}
	}
	@GetMapping("/getProductDetails/{pid}")
	public ResponseEntity<?> getProductDetailsById(@PathVariable long pid) {
		try {
			System.out.println("in getproduct by id"+ pid);
			ProductDetails product = productService.getProductById(pid);
			System.out.println(product);
			return ResponseEntity.status(HttpStatus.OK)
					.body(new EditResponse("success", product));

		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ErrorResponse("Something went wrong ", e.toString()));
		}

	}
}
