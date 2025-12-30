package com.hairdresser.managers.service.impl;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.hairdresser.managers.entities.UserDetailEntity;
import com.hairdresser.managers.entities.UserEntity;
import com.hairdresser.managers.exception.CustomExceptionModel.CustomBadRequestException;
import com.hairdresser.managers.exception.CustomExceptionModel.CustomForbiddenException;
import com.hairdresser.managers.exception.CustomExceptionModel.CustomInternalServerErrorException;
import com.hairdresser.managers.exception.CustomExceptionModel.CustomNotFoundException;
import com.hairdresser.managers.mapper.GetUserDetailsMapper;
import com.hairdresser.managers.model.GetUserResponse;
import com.hairdresser.managers.repository.UserDetailRepository;
import com.hairdresser.managers.repository.UserRepository;
import com.hairdresser.managers.service.GetUserDetailsService;
import com.hairdresser.managers.validation.UserIdValidations;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class GetUserDetailsServiceImpl implements GetUserDetailsService {
	
	private final UserRepository userRepository;
	
	private final UserDetailRepository userDetailRepository;
	
	private final GetUserDetailsMapper getUserDetailsMapper;

	@Override
	public GetUserResponse getUserDetails(String id) {
		UserIdValidations.validateuserId(id);
		var tokenInfo = SecurityContextHolder.getContext();
		var rolesFromToken = tokenInfo.getAuthentication().getAuthorities().stream().map(GrantedAuthority::getAuthority)
				.collect(Collectors.toList());
		var userFromToken = tokenInfo.getAuthentication().getName();
		UserEntity userFromDb = getUserFromDb(id);
		if (!rolesFromToken.contains("ADMIN") && !userFromDb.getUsername().equals(userFromToken)) {
			log.error("A normal user can only get its own information");
			throw new CustomForbiddenException("not_admin");
		}
		if (!userFromDb.getIsActive()) {
			log.error("User is not active");
			throw new CustomBadRequestException("inactive_user");
		}
		UserDetailEntity userDetailsFromDb = getUserDetailsFromDb(id);
		return getUserDetailsMapper.responseMapper(userFromDb.getUsername(), userDetailsFromDb);
	}
	
	private UserEntity getUserFromDb(String userId) {
		Optional<UserEntity> op = Optional.empty();
		try {
			op = userRepository.findById(Long.parseLong(userId));
			if (op.isEmpty()) {
				log.error("The user does not exist in the database");
				throw new CustomNotFoundException("Managers-0004");
			}
		} catch (DataAccessException e) {
			log.error("There was a problem trying to access the database");
			throw new CustomInternalServerErrorException("database_error");
		}
		return op.get();
	}
	
	private UserDetailEntity getUserDetailsFromDb(String userId) {
		Optional<UserDetailEntity> op = Optional.empty();
		try {
			op = userDetailRepository.findById(Long.parseLong(userId));
			if (op.isEmpty()) {
				log.error("The user does not exist in the database");
				throw new CustomNotFoundException("Managers-0004");
			}
		} catch (DataAccessException e) {
			log.error("There was a problem trying to access the database");
			throw new CustomInternalServerErrorException("database_error");
		}
		return op.get();
	}

}
