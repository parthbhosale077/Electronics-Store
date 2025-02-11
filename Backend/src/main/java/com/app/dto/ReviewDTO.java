package com.app.dto;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReviewDTO {
	
	@NotBlank(message = "Title is required")
	private String title;
	
	@NotBlank(message = "Description is required")
	private String description;
	
	private int rating;
	
	@NotBlank(message = "ProductId is required")
	private Long product;
	
	@NotBlank(message = "UserId is required")
	private Long user;
}
