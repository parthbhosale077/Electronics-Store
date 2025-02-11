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
public class CardDetails extends BaseEntity {
	
	@NotBlank(message = "Card number is required")
	@Column(length = 16, nullable = false)
	private String cardNumber;
	
	@NotBlank(message = "Name is required")
	@Column(length = 50, nullable = false)
	private String name;
	
	@NotBlank(message = "CVV is required")
	@Column(length = 3, nullable = false)
	private String cvv;
	
	@NotBlank(message = "Expiry details are required")
	@Column(length = 16, nullable = false)
	private String expiry;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private UserEntity user;
	
}
