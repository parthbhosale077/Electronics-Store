	package com.app.controller;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.CategoryDTO;
import com.app.dto.DataResponse;
import com.app.dto.EditResponse;
import com.app.dto.ErrorResponse;
import com.app.dto.OrderDetailsDto;
import com.app.dto.ProductDto;
import com.app.dto.ProductInventoryDTO;
import com.app.dto.SalesDTO;
import com.app.entities.DiscountDetails;
import com.app.entities.ProductDetails;
import com.app.service.IAdminService;
import com.app.service.ICategoryService;
import com.app.service.IOrderDetailsService;
import com.app.service.IProductInventoryService;
import com.app.service.IProductService;
import com.app.service.ISalesService;
import com.app.service.ImageHandlingService;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "*")
public class AdminController {
	@Autowired
	private IAdminService adminService;

	@Autowired
	private IProductInventoryService productInventoryService;

	@Autowired
	private ICategoryService categoryService;

	@Autowired
	private IProductService productService;

	@Autowired
	private IOrderDetailsService orderDetailsService;

	@Autowired
	private ISalesService salesService;

	@Autowired
	private ImageHandlingService imageService;

	@GetMapping("/viewAll")
	public ResponseEntity<?> showAllProducts() {
		return ResponseEntity.status(HttpStatus.OK).
				body(new DataResponse("success", productService.getAllProducts()));
	}

	@DeleteMapping("/deleteProduct/{productId}")
	public ResponseEntity<?> deleteProduct(@PathVariable Long productId) {
		return ResponseEntity.status(HttpStatus.OK).body(productService.deleteProduct(productId));
	}

	@PostMapping("/addProduct")
	public ResponseEntity<?> addProductDetails(@ModelAttribute ProductDto product) throws IOException {
		System.out.println("Inside addProduct");
		product.setImagePath(imageService.uploadImage(product.getImage()));
		return ResponseEntity.status(HttpStatus.CREATED).
				body(productService.addProductDetails(product));
	}

	@PostMapping("/addDiscount")
	public ResponseEntity<?> addDiscount(@RequestBody DiscountDetails discount)

	{
		System.out.println("add new discounts" + discount);

		return ResponseEntity.status(HttpStatus.CREATED).
				body(adminService.insertDiscount(discount));

	}

	@GetMapping("/view")
	public String showHomePage() {
		return "Admin Home reached";
	}

	@GetMapping("/viewUsers")
	public ResponseEntity<?> fetchAllUserDetails() {
		try {
			return ResponseEntity.status(HttpStatus.OK).
					body(new DataResponse("success", adminService.getAllUsers()));
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ErrorResponse("Something went wrong while fetching   ", e.toString()));
		}
	}

	@GetMapping("/viewDiscounts")
	public ResponseEntity<?> fetchAllDiscountDetails() {
		try {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new DataResponse("success", adminService.getAllDiscounts()));

		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ErrorResponse("Something went wrong while fetching   ", e.toString()));
		}
	}

//	@PostMapping("/addDiscount")
//
//	public DiscountDetails addDiscount(@RequestBody DiscountDetails discount) {
//		System.out.println("add new discounts" + discount);// id : null
//		return adminService.insertDiscount(discount);
//	}

	@PutMapping("/updateDiscount")
	public DiscountDetails updatediscountDetails(@RequestBody DiscountDetails discount) {
		System.out.println(" update  discount details" + discount);// not null id
		return adminService.updatediscountDetails(discount);
	}
//
//	@DeleteMapping("/{uid}")
//	public String deleteDiscountDetails(@PathVariable long uid) {
//
//		return adminService.deleteDiscountDetails(uid);
//	}

//	@DeleteMapping("/deleteUser/{userid}")
//	public String deleteUser(@PathVariable long userid) {
//
//		return adminService.deleteUser(userid);
//	}



	

	@DeleteMapping("/deleteDiscount/{uid}")
	public ResponseEntity<?> deleteDiscountDetails(@PathVariable long uid) {

		return ResponseEntity.status(HttpStatus.OK).
				body(adminService.deleteDiscountDetails(uid));
	}

	@GetMapping("/inventory")
	public ResponseEntity<?> getAllProductsFromInventory() {
		try {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new DataResponse("success", productInventoryService.getAllProductsFromInventory()));

		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ErrorResponse("Something went wrong while fetching inventory details ", e.toString()));
		}
	}

	@PutMapping("/inventory/updateQuantity")
	public ResponseEntity<?> updateQuantity(@RequestBody @Valid ProductInventoryDTO productInv) {

		try {
			// o.s.http.ResponseEntity(T body,HttpStatus stsCode)
			return ResponseEntity.status(HttpStatus.OK).
					body(new EditResponse("success",
					productInventoryService.updateInventoryStock(productInv.getInventoryId(), productInv.getQty())));

		} catch (RuntimeException e) {
			System.out.println("err in admin controller " + e);
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ErrorResponse("Something went wrong while updating product quantity ", e.toString()));
		}

	}

	@PutMapping("/removeProduct/{productId}")
	
	public ResponseEntity<?> removeProduct(@PathVariable long productId) {

		try {
			// o.s.http.ResponseEntity(T body,HttpStatus stsCode)
			return ResponseEntity.status(HttpStatus.OK).
					body(new EditResponse("success",
					productService.removeProduct(productId)));

		} catch (RuntimeException e) {
			System.out.println("err in admin controller " + e);
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ErrorResponse("Something went wrong while updating removing the product ", e.toString()));
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

	@GetMapping("/getOrderDetails/{status}")
	public ResponseEntity<?> getOrderDetailsByStatus(@PathVariable String status) {

		try {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new DataResponse("success", orderDetailsService.getOrdersByStatus(status)));

		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ErrorResponse("Something went wrong. Unable to fetch order details ", e.toString()));
		}
	}

	@PutMapping("/orders/updateStatus")
	public ResponseEntity<?> updateOrderStatus(@RequestBody OrderDetailsDto orderDto) {

		try {

			return ResponseEntity.status(HttpStatus.OK).body(new EditResponse("success",
					orderDetailsService.updateOrderStatus(orderDto.getId(), orderDto.getStatus())));

			// o.s.http.ResponseEntity(T body,HttpStatus stsCode)
			// return new ResponseEntity<>(),
			// HttpStatus.OK);
		} catch (RuntimeException e) {
			System.out.println("err in admin controller " + e);
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ErrorResponse("Something went wrong while updating quantity", e.toString()));
		}

	}

	@PostMapping("/addCategory")
	public ResponseEntity<?> addCategory(@RequestBody CategoryDTO category) {
		try {
			System.out.println(category.getName());
			return ResponseEntity.status(HttpStatus.OK).body(categoryService.addCategory(category));
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ErrorResponse("Category addition failed", e.toString()));
		}
	}
	
	//Add brand

	@PutMapping("/editProduct/{productId}")
	public ResponseEntity<?> editProductDetails(@ModelAttribute ProductDto product, @PathVariable long productId)
			throws IOException {
		try {
			System.out.println("Inside editBook");
			if (product.getImage() != null)
				product.setImagePath(imageService.uploadImage(product.getImage()));
			return ResponseEntity.status(HttpStatus.OK)
					.body(new EditResponse("success", productService.editProductDetails(product, productId)));

		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ErrorResponse("Something went wrong while updating book details ", e.toString()));
		}
	}

	@PostMapping("/getSalesDetailsByProduct")
	public ResponseEntity<?> getSalesDetailsByProduct(@RequestBody SalesDTO dto) {
		try {
			System.out.println("Start Date " + dto.getStartDate());
			System.out.println("End Date " + dto.getEndDate());
			return ResponseEntity.status(HttpStatus.OK).body(new DataResponse("success",
					salesService.findSalesOnProductByOrderDate(dto.getStartDate(), dto.getEndDate())));

		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.OK).body(new ErrorResponse("Something went wront", e.toString()));
		}
	}

	@PostMapping("/getSalesDetailsByCategory")
	public ResponseEntity<?> getSalesDetailsByCategory(@RequestBody SalesDTO dto) {
		try {
			System.out.println("Start Date " + dto.getStartDate());
			System.out.println("End Date " + dto.getEndDate());
			return ResponseEntity.status(HttpStatus.OK).body(new DataResponse("success",
					salesService.findSalesOnCategoryByOrderDate(dto.getStartDate(), dto.getEndDate())));

		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.OK).body(new ErrorResponse("Something went wront", e.toString()));
		}
	}
	
	@GetMapping("/getRemovedProducts")
	public ResponseEntity<?> getAllRemovedProducts() {
		try {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new DataResponse("success", productService.getRemovedProducts()));

		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ErrorResponse("Something went wrong while fetching product details ", e.toString()));
		}
	}
	
	@PutMapping("/makeProductAvailable/{productId}")
	public ResponseEntity<?> makeProductAvailable(@PathVariable long productId) {

		try {
			// o.s.http.ResponseEntity(T body,HttpStatus stsCode)
			return ResponseEntity.status(HttpStatus.OK).body(new EditResponse("success",
					productService.makeProductAvailable(productId)));

		} catch (RuntimeException e) {
			System.out.println("err in admin controller " + e);
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ErrorResponse("Something went wrong while updating the product status ", e.toString()));
		}

	}
}
