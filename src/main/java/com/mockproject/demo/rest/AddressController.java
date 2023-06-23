package com.mockproject.demo.rest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mockproject.demo.DTO.AddressDTOResponse;
import com.mockproject.demo.contraints.ResponseCode;
import com.mockproject.demo.model.Address;
import com.mockproject.demo.model.User;
import com.mockproject.demo.repository.AddressRepository;
import com.mockproject.demo.repository.UserRepository;
import com.mockproject.demo.service.AddressService;

@RestController
@RequestMapping("/address")
public class AddressController extends BaseRestController {
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private AddressService addressService;
	@Autowired
	private UserRepository userRepository;

	@PostMapping("")
	public ResponseEntity<?> addNewAddress(@RequestBody(required = false) Map<String, Object> newAddress) {
		try {
			if (ObjectUtils.isEmpty(newAddress) || ObjectUtils.isEmpty(newAddress.get("userId"))
					|| ObjectUtils.isEmpty(newAddress.get("street")) || ObjectUtils.isEmpty(newAddress.get("country"))
					|| ObjectUtils.isEmpty(newAddress.get("city"))) {
				return super.error(ResponseCode.NO_PARAM.getCode(), ResponseCode.NO_PARAM.getMessage());
			}
			User foundUser = this.userRepository.findById(Long.parseLong(newAddress.get("userId").toString()))
					.orElse(null);
			if (!ObjectUtils.isEmpty(foundUser)) {
				Address address = this.addressService.addNewAddress(newAddress, foundUser);
				return super.sucess(new AddressDTOResponse(address));
			}
			return super.error(ResponseCode.NOT_FOUND.getCode(), ResponseCode.NOT_FOUND.getMessage());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return super.error(ResponseCode.NO_CONTENT.getCode(), ResponseCode.NO_CONTENT.getMessage());
	}
	
	@GetMapping("")
	public ResponseEntity<?> getAllAddress(){
		try {
			List<Address> addresses = this.addressRepository.findAll();
			List<AddressDTOResponse> response = addresses.stream().map(AddressDTOResponse::new).toList();
			return super.sucess(response);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return super.error(ResponseCode.NO_CONTENT.getCode(), ResponseCode.NO_CONTENT.getMessage());
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateAddress(@PathVariable long id, @RequestBody(required = false) Map<String, Object> newAddress){
		try {
			if (ObjectUtils.isEmpty(newAddress) || ObjectUtils.isEmpty(id)
					|| ObjectUtils.isEmpty(newAddress.get("street")) || ObjectUtils.isEmpty(newAddress.get("country"))
					|| ObjectUtils.isEmpty(newAddress.get("city"))) {
				return super.error(ResponseCode.NO_PARAM.getCode(), ResponseCode.NO_PARAM.getMessage());
			}
			Address foundAddress = this.addressRepository.findById(id).orElse(null);
			if (!ObjectUtils.isEmpty(foundAddress)) {
				Address address = this.addressService.updateAddress(id, newAddress,foundAddress);
				return super.sucess(new AddressDTOResponse(address));
			}
			return super.error(ResponseCode.NOT_FOUND.getCode(), ResponseCode.NOT_FOUND.getMessage());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return super.error(ResponseCode.NO_CONTENT.getCode(), ResponseCode.NO_CONTENT.getMessage());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteAddress(@PathVariable long id){
		try {
			if(ObjectUtils.isEmpty(id)) {
				return super.error(ResponseCode.NO_PARAM.getCode(), ResponseCode.NO_PARAM.getMessage());
			}
			Address foundAddress = this.addressRepository.findById(id).orElse(null);
			if(ObjectUtils.isEmpty(foundAddress)) {
				return super.error(ResponseCode.NOT_FOUND.getCode(), ResponseCode.NOT_FOUND.getMessage());
			}
			this.addressService.deleteAddress(id);
			return super.sucess(new AddressDTOResponse(foundAddress));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return super.error(ResponseCode.NO_CONTENT.getCode(), ResponseCode.NO_CONTENT.getMessage());
	}
}
