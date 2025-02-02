package com.hairdresser.managers.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostCreateUserRequest {
	
	private String userName;
	
	private String password;
	
	private Role role;
	
	private Details details;

}
