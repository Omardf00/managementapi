package com.hairdresser.managers.exception;

import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.RequiredArgsConstructor;

@RestControllerAdvice
@RequiredArgsConstructor
@PropertySource("classpath:error/errors.properties")
public class GlobalHandler {
	
	private final Environment env;

    private CustomErrorResponse buildErrorResponse(String code) {
        String errorCode = env.getProperty(code + ".code", "UNKNOWN_ERROR");
        String message = env.getProperty(code + ".message", "Unknown error occurred");
        String description = env.getProperty(code + ".description", "No description available");

        return new CustomErrorResponse(errorCode, message, description);
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<CustomErrorResponse> handleCustomException(CustomException ex) {
    	CustomErrorResponse errorResponse = buildErrorResponse(ex.getCode());
        return new ResponseEntity<>(errorResponse, ex.getStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomErrorResponse> handleGenericException(Exception ex) {
    	CustomErrorResponse errorResponse = buildErrorResponse("internal.server.error");
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
	
}
