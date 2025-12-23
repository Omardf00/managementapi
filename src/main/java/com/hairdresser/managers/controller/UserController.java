package com.hairdresser.managers.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hairdresser.managers.model.PostCreateUserRequest;
import com.hairdresser.managers.model.PostCreateUserResponse;
import com.hairdresser.managers.service.CreateUserService;
import com.hairdresser.managers.service.InvalidateUserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/management")
@RequiredArgsConstructor
public class UserController {

	private final CreateUserService createUserService;

	private final InvalidateUserService invalidateUserService;

	@PostMapping("/sign_up")
	public ResponseEntity<PostCreateUserResponse> createUser(@Valid @RequestBody PostCreateUserRequest request,
			BindingResult result) {
		return new ResponseEntity<PostCreateUserResponse>(createUserService.createUser(request), HttpStatus.CREATED);
	}

	@PostMapping("/inactivate/{userId}")
	public ResponseEntity<Void> inactivateUser(@PathVariable String userId) {
		invalidateUserService.invalidateUser(userId);
		return ResponseEntity.noContent().build();
	}

}
