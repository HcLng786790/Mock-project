package com.mockproject.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mockproject.demo.model.VariantProduct;

@Repository
public interface VariantProductRepository extends JpaRepository<VariantProduct, Long>{

}
