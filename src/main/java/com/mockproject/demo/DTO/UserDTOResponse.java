package com.mockproject.demo.DTO;

import java.util.Iterator;
import java.util.Set;

import org.springframework.util.ObjectUtils;

import com.mockproject.demo.model.Role;
import com.mockproject.demo.model.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class UserDTOResponse {
	private Long id;
	private String fullName;
	private String userName;

	public UserDTOResponse(User user) {
		this.id = user.getId();
		this.fullName = user.getDisPlayName();
		this.userName=user.getName();
	}
}
