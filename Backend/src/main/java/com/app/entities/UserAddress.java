package com.app.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
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
public class UserAddress extends BaseEntity {
	//user_id (fk)	address_line_1	address_line_2	city	state	country	pincode
	
	@NotBlank(message = "Address line 1 is required")
	@Column(length = 200, nullable = false)
	private String addressLine1;
	
	@NotBlank(message = "Address line 2 is required")
	@Column(length = 200, nullable = false)
	private String addressLine2;
	
	@Column(length = 50, nullable = false)
	private String city;
	
	@Column(length = 50, nullable = false)
	private String state;
	
	@Column(length = 50, nullable = false)
	private String country;
	
	@Column(length = 10, nullable = false)
	private String pincode;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private UserEntity user;
}
