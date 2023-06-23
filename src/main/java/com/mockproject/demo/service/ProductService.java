package com.mockproject.demo.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mockproject.demo.model.Category;
import com.mockproject.demo.model.Product;
import com.mockproject.demo.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepository;

	
	public Product addNewProduct(long id,Map<String, Object> newProduct,Category category) {
		Product product = new Product();
		product.setName(newProduct.get("name").toString());
		product.setCategory(category);
		this.productRepository.save(product);
		return product;
	}
	
	public Page<Product> getByCategory(Category category,Pageable pageable){
		return this.productRepository.findByCategory(category, pageable);
	}
	
	public Product updateProduct(long id,Map<String, Object> newProduct,Product product) {
		product.setId(id);
		product.setName(newProduct.get("name").toString());
		this.productRepository.save(product);
		return product;
	}
}
