package com.app.entities;

import java.sql.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
public class OrderDetails extends BaseEntity

{

	// id (pk), user_id (fk), payment_id (fk), created at

	@ManyToOne
	private UserEntity user;// many to one

	@Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date createdAt;

	@NotBlank(message = "Order status is required")
	@Column(length = 16, nullable = false)
	// @Enumerated(EnumType.STRING)
	private String status;

	@OneToOne
	private UserAddress address;

	@Column(columnDefinition = "BOOLEAN")
	private boolean codStatus;

	@Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date statusUpdatedAt;

}
