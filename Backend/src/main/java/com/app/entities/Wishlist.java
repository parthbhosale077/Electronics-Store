package com.app.entities;

import java.util.HashSet;
import java.util.Set;


import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString

public class Wishlist extends BaseEntity {
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(nullable = false)
	private UserEntity user; 
	

	// Wishlist 1----->* Products
//	@ElementCollection 
//	@CollectionTable(name = "wishlist_products", joinColumns = @JoinColumn(name = "wishlist_id")) 																			// the coll table + FK col name
//	@OneToMany(fetch = FetchType.EAGER)
	@ManyToMany
	@Column
	private Set<ProductDetails> products = new HashSet<>();

	public Wishlist()
	{
		
	}

	public void addProduct(ProductDetails product) {
		products.add(product);
	}

	public void removeProduct(ProductDetails product) {
		products.remove(product);
	}
	
	public Set<ProductDetails> getProducts() {
		return products;
	}

	public void setProducts(Set<ProductDetails> products) {
		this.products = products;
	}


}
