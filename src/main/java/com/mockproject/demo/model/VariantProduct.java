package com.mockproject.demo.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class VariantProduct {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String model;
	private Double price;
	private String color;
	private Double size;
	
	@ManyToOne
	@JoinColumn(name="product_id")
	@JsonBackReference
	private Product product;
	
//	@OneToOne(mappedBy = "variantProduct",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
//	private CartLineItem cartLineItem;
	
	@OneToMany(mappedBy = "variantProduct",cascade = CascadeType.ALL,fetch = FetchType.LAZY )
	@JsonManagedReference
	private List<CartLineItem> cartLineItemrs;
}
