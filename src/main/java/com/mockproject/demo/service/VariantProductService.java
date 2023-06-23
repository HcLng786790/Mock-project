package com.mockproject.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mockproject.demo.model.CartLineItem;
import com.mockproject.demo.model.Product;
import com.mockproject.demo.model.VariantProduct;
import com.mockproject.demo.repository.VariantProductRepository;

@Service
public class VariantProductService {
	@Autowired
	private VariantProductRepository variantProductRepository;
	
	public VariantProduct addNewVariant(Map<String, Object> newVariant,Product product) {
		VariantProduct variantProduct = new VariantProduct();
		variantProduct.setName(newVariant.get("name").toString());
		variantProduct.setModel(newVariant.get("model").toString());
		variantProduct.setPrice(Double.parseDouble(newVariant.get("price").toString()));
		variantProduct.setColor(newVariant.get("color").toString());
		variantProduct.setSize(Double.parseDouble(newVariant.get("size").toString()));
		variantProduct.setProduct(product);
		List<CartLineItem> cartLineItems = new ArrayList<>();
		variantProduct.setCartLineItemrs(cartLineItems);
		this.variantProductRepository.save(variantProduct);
		return variantProduct;
	}
	
	public VariantProduct updateVariant(long id , Map<String, Object> newVariant,VariantProduct variantProduct) {
		variantProduct.setName(newVariant.get("name").toString());
		variantProduct.setModel(newVariant.get("model").toString());
		variantProduct.setPrice(Double.parseDouble(newVariant.get("price").toString()));
		variantProduct.setColor(newVariant.get("color").toString());
		variantProduct.setSize(Double.parseDouble(newVariant.get("size").toString()));
		variantProduct.setId(id);
		this.variantProductRepository.save(variantProduct);
		return variantProduct;
	}
	
}
