package com.mockproject.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mockproject.demo.model.Category;
import com.mockproject.demo.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	Page<Product> findByCategory(Category category,Pageable pageable);

}
