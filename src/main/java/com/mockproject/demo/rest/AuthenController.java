package com.mockproject.demo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mockproject.demo.DTO.AuthenDTORequest;
import com.mockproject.demo.DTO.AuthenDTOResponse;
import com.mockproject.demo.contraints.ResponseCode;
import com.mockproject.demo.utils.JwtUtils;



@RestController
@RequestMapping(path = "/login")
public class AuthenController extends BaseRestController {
	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping
	public ResponseEntity<?> login(@RequestBody AuthenDTORequest authen) {
		try {
			this.authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(authen.getUserName(), authen.getPassword()));

			String token = JwtUtils.generateToken(authen.getUserName());
			AuthenDTOResponse response = new AuthenDTOResponse(token, "Dang nhap thanh cong!");
			return super.sucess(response);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return error(ResponseCode.NO_CONTENT.getCode(), ResponseCode.NO_CONTENT.getMessage());
	}
}
