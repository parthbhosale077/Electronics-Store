package com.app.entities;

import java.sql.*;

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
public class DiscountDetails extends BaseEntity {
	
	//id	coupon_code	status 	valid_date	created_at	percentage					
							

	@NotBlank(message = "Coupon code is required")
	@Column(nullable = false)
	private String couponCode;
	
	@NotBlank(message = "Status is required")
	@Column(nullable = false)
	private String status;
	
	@Column(nullable = false)
	private Date validDate;
	
	@Column(nullable = false)
	private Double percentage;
	
	
	@Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date createdAt;
	
	
}
