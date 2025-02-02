package com.hairdresser.managers.service;

import com.hairdresser.managers.model.PostCreateUserRequest;
import com.hairdresser.managers.model.PostCreateUserResponse;

public interface UserService {
	
	PostCreateUserResponse createUser(PostCreateUserRequest request);

}
