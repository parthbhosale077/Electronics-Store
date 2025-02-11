package com.app.entities;

import java.sql.*;
import javax.persistence.Column;
import javax.persistence.Entity;
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
public class TransactionRecords extends BaseEntity {
	
	//id	amount	order_id (fk)	date							

	@NotBlank(message = "amount is required")
	@Column(nullable = false)
	private double amount;
	
	@OneToOne
	OrderDetails order;
	
	@Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date transactionTimestamp;
}
