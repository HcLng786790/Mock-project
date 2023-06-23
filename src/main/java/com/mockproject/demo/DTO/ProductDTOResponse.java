package com.mockproject.demo.DTO;


import java.util.List;

import org.springframework.util.ObjectUtils;

import com.mockproject.demo.model.Product;
import com.mockproject.demo.model.VariantProduct;

import lombok.Data;

@Data
public class ProductDTOResponse {
	private Long id;
	private String name;
	private String category_name;
//	private List<VariantProduct> variantProducts;
	public ProductDTOResponse(Product product) {
		this.id= product.getId();
		this.name=product.getName();
		if(!ObjectUtils.isEmpty(product.getCategory())) {
			this.category_name=product.getCategory().getName();
		}
//		if(!ObjectUtils.isEmpty(product.getVariantProducts())) {
//			this.variantProducts=product.getVariantProducts();
//		}
	}
}
