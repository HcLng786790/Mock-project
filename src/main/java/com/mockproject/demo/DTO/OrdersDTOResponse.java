package com.mockproject.demo.DTO;

import java.util.List;

import org.springframework.util.ObjectUtils;

import com.mockproject.demo.model.Orders;

import lombok.Data;

@Data
public class OrdersDTOResponse {
	private Long id;
	private List<CartLineItemDTOResponse> cartLineItems;
	
	public OrdersDTOResponse(Orders orders) {
		this.id=orders.getId();
		if(!ObjectUtils.isEmpty(orders.getUser())) {
			this.cartLineItems=orders.getUser().getCart().getCartLineItems().stream()
					.map(CartLineItemDTOResponse::new).toList();
		}
	}
}
