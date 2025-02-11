package com.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProductInventory extends BaseEntity {
	
	//product_id (fk)	quantity									
	@OneToOne
	ProductDetails product;
	
	//@NotBlank(message = "Quantity is required")
	@Column(nullable = false)
	private double quantity;
	
	
}
