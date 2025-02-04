package com.hairdresser.managers.exception;

import org.springframework.http.HttpStatus;

public class CustomException extends RuntimeException{
	
	private static final long serialVersionUID = 5704700090768245763L;
	private final String code;
    private final HttpStatus status;

    public CustomException(String code, HttpStatus status) {
        this.code = code;
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public HttpStatus getStatus() {
        return status;
    }

}
