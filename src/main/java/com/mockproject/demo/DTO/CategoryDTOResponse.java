package com.mockproject.demo.DTO;

import java.util.List;

import org.springframework.util.ObjectUtils;

import com.mockproject.demo.model.Category;
import com.mockproject.demo.model.Product;

import lombok.Data;

@Data
public class CategoryDTOResponse {
	private Long id;
	private String name;
	private List<ProductDTOResponse> products;

	public CategoryDTOResponse(Category category) {
		this.id = category.getId();
		this.name = category.getName();
		if (!ObjectUtils.isEmpty(category.getProducts())) {
			this.products = category.getProducts().stream().map(ProductDTOResponse::new).toList();
		}
	}
}
