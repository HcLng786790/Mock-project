package com.mockproject.demo.DTO;

import org.springframework.util.ObjectUtils;

import com.mockproject.demo.model.CartLineItem;

import lombok.Data;

@Data
public class CartLineItemDTOResponse {
	private Long id;
	private int quantity;
	private String variantProductName;
	private Long cartId;
	
	public CartLineItemDTOResponse(CartLineItem cartLineItem) {
		this.id=cartLineItem.getId();
		this.quantity=cartLineItem.getQuantity();
		if(!ObjectUtils.isEmpty(cartLineItem.getVariantProduct())) {
			this.variantProductName=cartLineItem.getVariantProduct().getName();
		}
		if(!ObjectUtils.isEmpty(cartLineItem.getCart())) {
			this.cartId=cartLineItem.getCart().getId();
		}
	}
}
