package com.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
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
public class PaymentDetails extends BaseEntity
{

	//id (pk)	order_id (fk)	status	amount
	
	
	@OneToOne
	private OrderDetails order;//one to one
	
	@Column(length = 20, nullable = false)
	private String status;
	
	@Column(length = 20, nullable = false)
	private double amount;
	
}
