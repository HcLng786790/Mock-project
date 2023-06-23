package com.mockproject.demo.contraints;

public enum ResponseCode {
	SUCESS(200,"OK"),
	NOT_FOUND(404,"Not found"),
	NO_PARAM(6001,"No param"),
	NO_CONTENT(2004, "No content"),
	INTERNAL_SERVER_ERROR(5000, "Internal server error"),
	DATA_INVALID_EXIT(2023,"User is already exit"),
	NOT_FOUND_ADDRESS(2024,"Not found address");
	
	private int code;
	private String message;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	private ResponseCode(int code , String message) {
		this.code=code;
		this.message=message;
	}
}
