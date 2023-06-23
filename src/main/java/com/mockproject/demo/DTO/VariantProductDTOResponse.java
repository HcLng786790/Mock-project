package com.mockproject.demo.DTO;

import org.springframework.util.ObjectUtils;

import com.mockproject.demo.model.VariantProduct;

import lombok.Data;

@Data
public class VariantProductDTOResponse {
	private Long id;
	private String model;
	private String name;
	private Double price;
	private String color;
	private Double size;
	private Long productId;
	public VariantProductDTOResponse(VariantProduct variantProduct) {
		this.id=variantProduct.getId();
		this.name=variantProduct.getName();
		this.model=variantProduct.getModel();
		this.price=variantProduct.getPrice();
		this.color=variantProduct.getColor();
		this.size=variantProduct.getSize();
		if(!ObjectUtils.isEmpty(variantProduct.getProduct())) {
			this.productId=variantProduct.getProduct().getId();
		}
	}
}
