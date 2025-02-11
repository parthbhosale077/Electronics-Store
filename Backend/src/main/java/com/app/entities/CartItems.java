package com.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


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
@Table(uniqueConstraints = 
		@UniqueConstraint(columnNames = {"user_id","product_id","quantity"}))

public class CartItems extends BaseEntity
{

	//id 	product_id (fk)	quantity

	//need user id as a field
	@ManyToOne
	private UserEntity user;
	
	@OneToOne
	private ProductDetails product;//one to many x // one to one
	
	@Column(length = 20, nullable = false)
	private int quantity;
	
	
}
