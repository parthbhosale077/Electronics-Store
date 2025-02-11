package com.app.entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

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
public class ReviewDetails extends BaseEntity {
	
	
	@NotBlank(message = "Title is required")
	@Column(length = 200, nullable = false)
	private String title;
	
	@NotBlank(message = "Description is required")
	@Column(length = 200, nullable = false)
	private String description;
	
	private int rating;
	
	@Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date DateAdded;
	
	@ManyToOne
	private ProductDetails product;
	
	@ManyToOne
	private UserEntity user;
	
}
