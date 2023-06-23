package com.mockproject.demo.rest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mockproject.demo.DTO.UserDTOResponse;
import com.mockproject.demo.contraints.ResponseCode;
import com.mockproject.demo.model.User;
import com.mockproject.demo.repository.UserRepository;
import com.mockproject.demo.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController extends BaseRestController {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping("")
	public ResponseEntity<?> getAllUser() {
		try {
			List<User> users = this.userService.getAllUser();
			List<UserDTOResponse> response = users.stream().map(UserDTOResponse::new).toList();
			return super.sucess(response);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return super.error(ResponseCode.NO_CONTENT.getCode(), ResponseCode.NO_CONTENT.getMessage());
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<?> updateUserByMap(@PathVariable long id,
			@RequestBody(required = false) Map<String, Object> newUser) {
		try {
			if (ObjectUtils.isEmpty(newUser)) {
				return super.error(ResponseCode.NO_PARAM.getCode(), ResponseCode.NO_PARAM.getMessage());
			}

//			UserDTORequest userDTORequest = new UserDTORequest(newUser);

			if (ObjectUtils.isEmpty(newUser.get("userName")) || ObjectUtils.isEmpty(newUser.get("email"))
					|| ObjectUtils.isEmpty(newUser.get("password"))) {
				return super.error(ResponseCode.NO_PARAM.getCode(), ResponseCode.NO_PARAM.getMessage());
			}

			User foundUser = this.userRepository.findById(id).orElse(null);
			if (ObjectUtils.isEmpty(foundUser)) {
				return super.error(ResponseCode.NOT_FOUND.getCode(), ResponseCode.NOT_FOUND.getMessage());
			}
			foundUser.setDisPlayName(newUser.get("fullName").toString());
			foundUser.setEmail(newUser.get("email").toString());
			foundUser.setPhone(newUser.get("phone").toString());
			foundUser.setName(newUser.get("userName").toString());
			foundUser.setPassword(this.passwordEncoder.encode(newUser.get("password").toString()));
			this.userRepository.save(foundUser);
			return super.sucess(new UserDTOResponse(foundUser));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return super.error(ResponseCode.NO_CONTENT.getCode(), ResponseCode.NO_CONTENT.getMessage());
	}

	@PostMapping("")
	public ResponseEntity<?> addNewUser(@RequestBody(required = false) Map<String, Object> newUser) {
		try {
			if (ObjectUtils.isEmpty(newUser) || ObjectUtils.isEmpty(newUser.get("fullName"))
					|| ObjectUtils.isEmpty(newUser.get("userName")) || ObjectUtils.isEmpty(newUser.get("email"))
					|| ObjectUtils.isEmpty(newUser.get("password")) || ObjectUtils.isEmpty(newUser.get("phone"))) {
				return super.error(ResponseCode.NO_PARAM.getCode(), ResponseCode.NO_PARAM.getMessage());
			}
			
			User foundUser = this.userRepository.findByName(newUser.get("userName").toString()).orElse(null);
			if (!ObjectUtils.isEmpty(foundUser)) {
				return super.error(ResponseCode.DATA_INVALID_EXIT.getCode(), ResponseCode.DATA_INVALID_EXIT.getMessage());
			}
			User user=this.userService.addNewUser(newUser);
			return super.sucess(new UserDTOResponse(user));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return super.error(ResponseCode.NO_CONTENT.getCode(), ResponseCode.NO_CONTENT.getMessage());

	}

}
