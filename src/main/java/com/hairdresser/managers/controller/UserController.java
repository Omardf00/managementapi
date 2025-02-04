package com.hairdresser.managers.controller;

import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hairdresser.managers.exception.CustomExceptionModel.CustomBadRequestException;
import com.hairdresser.managers.model.PostCreateUserRequest;
import com.hairdresser.managers.model.PostCreateUserResponse;
import com.hairdresser.managers.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/management")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	@PostMapping("/sign_up")
	public ResponseEntity<PostCreateUserResponse> createUser(@Valid @RequestBody PostCreateUserRequest request,
			BindingResult result) {
		return new ResponseEntity<PostCreateUserResponse>(userService.createUser(request), HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<?> test() throws BadRequestException {
		throw new CustomBadRequestException("test");
	}

}
