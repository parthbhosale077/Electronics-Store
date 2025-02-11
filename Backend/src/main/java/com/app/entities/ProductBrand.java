package com.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
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
public class ProductBrand extends BaseEntity {
	
	@NotBlank(message = "Category name is required")
	@Column(length = 50, nullable = false, unique = true)
	private String name;
}
