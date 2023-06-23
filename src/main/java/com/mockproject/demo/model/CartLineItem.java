package com.mockproject.demo.model;


import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CartLineItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private boolean isDeleted;
	private int quantity;
	
	@ManyToOne
	@JoinColumn(name="cart_id")
	@JsonBackReference
	private Cart cart;
	
//	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
//	@JoinColumn(name="variant_product_id")
//	private VariantProduct variantProduct;
	
	@ManyToOne
	@JoinColumn(name="variant_product_id")
	@JsonBackReference
	private VariantProduct variantProduct;
}
