package com.hairdresser.managers.service.impl;

import java.util.Optional;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.hairdresser.managers.entities.UserEntity;
import com.hairdresser.managers.exception.CustomExceptionModel.CustomBadRequestException;
import com.hairdresser.managers.exception.CustomExceptionModel.CustomInternalServerErrorException;
import com.hairdresser.managers.exception.CustomExceptionModel.CustomNotFoundException;
import com.hairdresser.managers.repository.UserRepository;
import com.hairdresser.managers.service.InvalidateUserService;
import com.hairdresser.managers.validation.UserIdValidations;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class InvalidateUserServiceImpl implements InvalidateUserService{
	
	private final UserRepository userRepositoty;

	@Override
	public void invalidateUser(String userId) {
		UserIdValidations.validateuserId(userId);
		var user = getUserFromDb(userId);
		checkActivity(user);
		inactivateUser(user);
	}
	
	private UserEntity getUserFromDb(String userId) {
		Optional<UserEntity> op = Optional.empty();
		try {
			op = userRepositoty.findById(Long.parseLong(userId));
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
	
	private void checkActivity(UserEntity user) {
		if (!user.getIsActive()) {
			log.error("User is not active");
			throw new CustomBadRequestException("inactive_user");
		}
	}
	
	private void inactivateUser(UserEntity user) {
		user.setIsActive(false);
		try {
			userRepositoty.save(user);
		} catch (DataAccessException e) {
			log.error("There was a problem trying to access the database");
			throw new CustomInternalServerErrorException("database_error");
		}
	}

}