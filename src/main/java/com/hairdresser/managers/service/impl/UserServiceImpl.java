package com.hairdresser.managers.service.impl;

import org.springframework.stereotype.Service;

import com.hairdresser.managers.entities.UserDetailEntity;
import com.hairdresser.managers.entities.UserEntity;
import com.hairdresser.managers.mapper.PostCreateUserMapper;
import com.hairdresser.managers.model.PostCreateUserRequest;
import com.hairdresser.managers.model.PostCreateUserResponse;
import com.hairdresser.managers.repository.UserDetailRepository;
import com.hairdresser.managers.repository.UserRepository;
import com.hairdresser.managers.service.UserService;
import com.hairdresser.managers.utils.Constants;
import com.hairdresser.managers.utils.Utils;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	private final UserDetailRepository userDetailRepository;

	private final PostCreateUserMapper postCreateUserMapper;

	private final Utils utils;

	@Transactional
	@Override
	public PostCreateUserResponse createUser(PostCreateUserRequest request) {
		insertIntoUsers(request);
		insertIntoUserDetails(request);
		return postCreateUserMapper.responseMapper(Constants.USER_CREATED_MESSAGE);
	}

	private void insertIntoUsers(PostCreateUserRequest request) {
		log.info("Attempting to insert into users");
		UserEntity entity = postCreateUserMapper.userEntityMapper(request);
		entity.setPassword(utils.encodePassword(request.getPassword()));
		userRepository.save(entity);
	}

	private void insertIntoUserDetails(PostCreateUserRequest request) {
		log.info("Attempting to insert into user_details");
		UserDetailEntity entity = postCreateUserMapper.userDetailEntityMapper(request);
		userDetailRepository.save(entity);
	}

}
