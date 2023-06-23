package com.mockproject.demo.rest;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mockproject.demo.DTO.CartLineItemDTOResponse;
import com.mockproject.demo.contraints.ResponseCode;
import com.mockproject.demo.model.Cart;
import com.mockproject.demo.model.CartLineItem;
import com.mockproject.demo.model.Product;
import com.mockproject.demo.model.User;
import com.mockproject.demo.model.VariantProduct;
import com.mockproject.demo.repository.CartLineItemRepository;
import com.mockproject.demo.repository.CartRepository;
import com.mockproject.demo.repository.UserRepository;
import com.mockproject.demo.repository.VariantProductRepository;
import com.mockproject.demo.service.CartLineItemService;

@RestController
@RequestMapping("/cart_line_item")
public class CartLineItemController extends BaseRestController {
	@Autowired
	private CartLineItemService cartLineItemService;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private VariantProductRepository variantProductRepository;

	@Autowired
	private CartLineItemRepository cartLineItemRepository;
	
	@Autowired
	private CartRepository cartRepository;

	@PostMapping("/addNewCartLine/{userId}")
	public ResponseEntity<?> addNewCartLine(@PathVariable long userId,
			@RequestBody(required = false) Map<String, Object> newCartLine) {
		try {
			if (ObjectUtils.isEmpty(newCartLine) || ObjectUtils.isEmpty(userId)
					|| ObjectUtils.isEmpty(newCartLine.get("variantProductId"))
					|| ObjectUtils.isEmpty(newCartLine.get("quantity"))) {
				return super.error(ResponseCode.NO_PARAM.getCode(), ResponseCode.NO_PARAM.getMessage());
			}
			VariantProduct foundVariantProduct = this.variantProductRepository
					.findById(Long.parseLong(newCartLine.get("variantProductId").toString())).orElse(null);
			User foundUser = this.userRepository.findById(userId).orElse(null);
			if (ObjectUtils.isEmpty(foundUser) || ObjectUtils.isEmpty(foundVariantProduct)) {
				return super.error(ResponseCode.NOT_FOUND.getCode(), ResponseCode.NOT_FOUND.getMessage());
			}
			Cart foundCart = this.cartRepository.findById(foundUser.getCart().getId()).orElse(null);
			CartLineItem cartLineItem = this.cartLineItemService.addNewCartLineItem(newCartLine, foundCart, foundVariantProduct);
			return super.sucess(new CartLineItemDTOResponse(cartLineItem));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return super.error(ResponseCode.NO_CONTENT.getCode(), ResponseCode.NO_CONTENT.getMessage());
	}
}
