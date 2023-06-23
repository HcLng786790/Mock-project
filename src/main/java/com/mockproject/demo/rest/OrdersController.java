package com.mockproject.demo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mockproject.demo.DTO.OrdersDTOResponse;
import com.mockproject.demo.contraints.ResponseCode;
import com.mockproject.demo.model.Address;
import com.mockproject.demo.model.CartLineItem;
import com.mockproject.demo.model.Orders;
import com.mockproject.demo.model.User;
import com.mockproject.demo.repository.AddressRepository;
import com.mockproject.demo.repository.CartLineItemRepository;
import com.mockproject.demo.repository.UserRepository;
import com.mockproject.demo.service.OrdersService;

@RestController
@RequestMapping("/orders")
public class OrdersController extends BaseRestController {
	@Autowired
	private OrdersService ordersService;

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private CartLineItemRepository cartLineItemRepository;
	
	@PostMapping("")
	public ResponseEntity<?> order(@RequestParam(required = false,name="user_id") long id 
			,@RequestParam(required = false,name="address_id") long addressId){
		try {
			if(ObjectUtils.isEmpty(id)||ObjectUtils.isEmpty(addressId)) {
				return super.error(ResponseCode.NO_PARAM.getCode(), ResponseCode.NO_PARAM.getMessage());
			}
			User foundUser = this.userRepository.findById(id).orElse(null);
			if(ObjectUtils.isEmpty(foundUser)) {
				return super.error(ResponseCode.NOT_FOUND.getCode(), ResponseCode.NOT_FOUND.getMessage());
			}
			Address foundAddress = this.addressRepository.findByIdAndUserId(addressId, id).orElse(null);
			if(ObjectUtils.isEmpty(foundAddress)) {
				return super.error(ResponseCode.NOT_FOUND_ADDRESS.getCode(), ResponseCode.NOT_FOUND_ADDRESS.getMessage());
			}
			
			List<CartLineItem> cartLineItems = foundUser.getCart().getCartLineItems();
			Orders orders = this.ordersService.order(cartLineItems, foundUser, foundAddress);
			for (CartLineItem cartLineItem : cartLineItems) {
				cartLineItem.setDeleted(true);
			}
			this.cartLineItemRepository.saveAll(cartLineItems);
			return super.sucess(new OrdersDTOResponse(orders));
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return super.error(ResponseCode.NO_CONTENT.getCode(), ResponseCode.NO_CONTENT.getMessage());
	}
	
	
}
