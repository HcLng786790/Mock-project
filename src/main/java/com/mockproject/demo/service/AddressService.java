package com.mockproject.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mockproject.demo.model.Address;
import com.mockproject.demo.model.Orders;
import com.mockproject.demo.model.User;
import com.mockproject.demo.repository.AddressRepository;

@Service
public class AddressService  {
	@Autowired
	private AddressRepository addressRepository;
	
	public Address addNewAddress(Map<String, Object> newAddress,User user) {
		Address address = new Address();
		address.setUser(user);
		address.setCity(newAddress.get("city").toString());
		address.setCountry(newAddress.get("country").toString());
		List<Orders> orders= new ArrayList<>();
		address.setOrders(orders);
		address.setStreet(newAddress.get("street").toString());
		this.addressRepository.save(address);
		return address;
	}
	
	public Address updateAddress(long id,Map<String, Object> newAddress,Address address) {
		address.setCity(newAddress.get("city").toString());
		address.setCountry(newAddress.get("country").toString());
		address.setStreet(newAddress.get("street").toString());
		this.addressRepository.save(address);
		address.setId(id);
		return address;
	}
	
	public void deleteAddress(long id) {
		 this.addressRepository.deleteById(id);
	}
}
