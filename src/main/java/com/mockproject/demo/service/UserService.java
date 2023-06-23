package com.mockproject.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mockproject.demo.model.Address;
import com.mockproject.demo.model.Cart;
import com.mockproject.demo.model.Orders;
import com.mockproject.demo.model.Role;
import com.mockproject.demo.model.User;
import com.mockproject.demo.repository.RoleRepository;
import com.mockproject.demo.repository.UserRepository;


@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepository roleRepository;
	public List<User> getAllUser(){
		return this.userRepository.findAll();
	}
	
	public User addNewUser(Map<String, Object> newUser) {
		User user = new User();
		user.setDisPlayName(newUser.get("fullName").toString());
		user.setEmail(newUser.get("email").toString());
		user.setName(newUser.get("userName").toString());
		user.setPassword(this.passwordEncoder.encode(newUser.get("password").toString()));
		user.setPhone(newUser.get("phone").toString());
		user.setCart(new Cart());
		user.setRoles(this.roleRepository.findByName("USER"));
		List<Address> addresses = new ArrayList<>();
		user.setAddresses(addresses);
		List<Orders> orders = new ArrayList<>();
		user.setOrders(orders);
		this.userRepository.save(user);
		return user;
	}
}
