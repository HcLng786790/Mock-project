package com.mockproject.demo.rest;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BaseRestController {
	public ResponseEntity<?> sucess(Object data){
		Map<String, Object> reponse = new HashMap<>();
		reponse.put("code", 200);
		reponse.put("message", "OK");
		reponse.put("data", data);
		return new ResponseEntity<>(reponse,HttpStatus.OK);
	}
	
	public ResponseEntity<?> error(int code,String message){
		Map<String, Object> reponse = new HashMap<>();
		reponse.put("code", code);
		reponse.put("message", message);
		reponse.put("data", null);
		return new ResponseEntity<>(reponse,HttpStatus.OK);
	}
}
