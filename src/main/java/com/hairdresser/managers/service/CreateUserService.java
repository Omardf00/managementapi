package com.hairdresser.managers.service;

import com.hairdresser.managers.model.PostCreateUserRequest;
import com.hairdresser.managers.model.PostCreateUserResponse;

public interface CreateUserService {
	
	PostCreateUserResponse createUser(PostCreateUserRequest request);

}
