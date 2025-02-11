package com.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
public class ProductDetails extends BaseEntity {
	
	@NotBlank(message = "Name is required")
	@Column(length = 50, nullable = false)
	private String name;
	
	//@NotBlank(message = "Price info is required")
	//@Column(length = 16, nullable = false)
	private double price;
	
	//Specification
	
	@NotBlank(message = "Image path is required")
	@Column(length = 100, nullable = false)
	private String imagePath;
	
	@NotBlank(message = "Description details required")
	@Column(length = 500, nullable = false)
	private String description;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private ProductCategory category;
	
	@NotBlank(message = "Publisher info is required")
	@Column(length = 50, nullable = false)
	private String brand;
	
	@Column(columnDefinition = "BOOLEAN")
	private boolean removedStatus;
}
