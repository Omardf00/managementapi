package com.hairdresser.managers.service.impl;

import java.util.Optional;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.hairdresser.managers.entities.UserDetailEntity;
import com.hairdresser.managers.entities.UserEntity;
import com.hairdresser.managers.exception.CustomExceptionModel.CustomBadRequestException;
import com.hairdresser.managers.exception.CustomExceptionModel.CustomInternalServerErrorException;
import com.hairdresser.managers.mapper.PostCreateUserMapper;
import com.hairdresser.managers.model.PostCreateUserRequest;
import com.hairdresser.managers.model.PostCreateUserResponse;
import com.hairdresser.managers.repository.UserDetailRepository;
import com.hairdresser.managers.repository.UserRepository;
import com.hairdresser.managers.service.CreateUserService;
import com.hairdresser.managers.utils.Constants;
import com.hairdresser.managers.utils.Utils;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreateUserServiceImpl implements CreateUserService {

	private final UserRepository userRepository;

	private final UserDetailRepository userDetailRepository;

	private final PostCreateUserMapper postCreateUserMapper;

	private final Utils utils;

	@Transactional
	@Override
	public PostCreateUserResponse createUser(PostCreateUserRequest request) {
		checkUserName(request);
		insertIntoUsers(request);
		insertIntoUserDetails(request);
		return postCreateUserMapper.responseMapper(Constants.USER_CREATED_MESSAGE);
	}

	private void checkUserName(PostCreateUserRequest request) {
		try {
			Optional<UserEntity> op = Optional.ofNullable(userRepository.findByUserName(request.getUserName()));
			if (!op.isEmpty()) {
				log.error("There is already a user with that name");
				throw new CustomBadRequestException("Managers-0007");
			}
		} catch (DataAccessException e) {
			log.error("There is already a user with that name");
			throw new CustomInternalServerErrorException("database_error");
		}
	}

	private void insertIntoUsers(PostCreateUserRequest request) {
		UserEntity entity = postCreateUserMapper.userEntityMapper(request);
		entity.setPassword(utils.encodePassword(request.getPassword()));
		userRepository.save(entity);
	}

	private void insertIntoUserDetails(PostCreateUserRequest request) {
		UserDetailEntity entity = postCreateUserMapper.userDetailEntityMapper(request);
		userDetailRepository.save(entity);
	}

}
