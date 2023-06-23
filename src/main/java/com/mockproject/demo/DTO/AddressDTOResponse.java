package com.mockproject.demo.DTO;

import org.springframework.util.ObjectUtils;

import com.mockproject.demo.model.Address;

import lombok.Data;

@Data
public class AddressDTOResponse {
	private Long id;
	private String street;
	private String city;
	private String country;
	private Long userId;
	
	public AddressDTOResponse(Address address) {
		this.id = address.getId();
		this.street=address.getStreet();
		this.city= address.getCity();
		this.country=address.getCountry();
		if(!ObjectUtils.isEmpty(address.getUser())) {
			this.userId=address.getUser().getId();
		}
	}
}
