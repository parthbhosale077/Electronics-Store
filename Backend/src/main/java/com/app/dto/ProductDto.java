package com.app.dto;

import javax.validation.constraints.NotBlank;

import org.springframework.web.multipart.MultipartFile;

import com.app.entities.ProductCategory;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductDto {
	
	@NotBlank(message = "Name is required")
	private String name;
	
	private double price;
	
	@NotBlank(message = "Image is required")
	private MultipartFile image;
	
	@JsonProperty(access =Access.READ_ONLY)
	private String imagePath;
	
	@NotBlank(message = "Description details required")
	private String description;
	
	@NotBlank(message = "Category is required")
	private ProductCategory category;
	
	@NotBlank(message = "Brand is required")
	private String brand;
	
}
