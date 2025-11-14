package com.admin.responseData;

import org.springframework.http.HttpStatus;

public class ResponseData {

	private Object value;
	
	private String message;
	
	private HttpStatus httpCode;

	public ResponseData(Object value, String message, HttpStatus httpCode) {
		super();
		this.value = value;
		this.message = message;
		this.httpCode = httpCode;
	}

	public ResponseData() {
		super();
		
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public HttpStatus getHttpCode() {
		return httpCode;
	}

	public void setHttpCode(HttpStatus httpCode) {
		this.httpCode = httpCode;
	}
	
}
