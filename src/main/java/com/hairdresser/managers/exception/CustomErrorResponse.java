package com.hairdresser.managers.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomErrorResponse {
	
	private String error;
	
    private String message;
    
    private String description;

}
