package com.mockproject.demo.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mockproject.demo.model.Address;
import com.mockproject.demo.model.CartLineItem;
import com.mockproject.demo.model.Orders;
import com.mockproject.demo.model.User;
import com.mockproject.demo.repository.OrdersRepository;

@Service
public class OrdersService  {
	@Autowired
	private OrdersRepository ordersRepository;
	@Autowired
	private CartService cartService;
	
	public Orders order(List<CartLineItem> cartLineItems,User user,Address address) {
		Orders orders = new Orders();
		orders.setDeliveryTime(new Date());
		orders.setOrderDate(new Date());
		orders.setUser(user);
		orders.setAddress(address);
		orders.setToatalAmount(this.cartService.getTotalAmount(user.getCart()));
		this.ordersRepository.save(orders);
		return orders;
	}
}
