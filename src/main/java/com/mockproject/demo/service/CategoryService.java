package com.mockproject.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mockproject.demo.model.Category;
import com.mockproject.demo.model.Product;
import com.mockproject.demo.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	public Category addNewCategory(Map<String, Object> newCategory) {
		Category category = new Category();
		category.setName(newCategory.get("name").toString());
		List<Product> products = new ArrayList<>();
		category.setProducts(products);
		this.categoryRepository.save(category);
		return category;
	}
}
