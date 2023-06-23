package com.mockproject.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mockproject.demo.model.Cart;
import com.mockproject.demo.model.CartLineItem;

@Service
public class CartService {
	
	public int getTotalAmount(Cart cart) {
		int sum=0;
		List<CartLineItem> cartLineItems = cart.getCartLineItems();
		for (CartLineItem cartLineItem : cartLineItems) {
			sum+=cartLineItem.getQuantity();
		}
		return sum;
	}
}
