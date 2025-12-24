package com.hairdresser.managers.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.hairdresser.managers.entities.RoleEntity;
import com.hairdresser.managers.entities.UserEntity;
import com.hairdresser.managers.exception.CustomExceptionModel.CustomBadRequestException;
import com.hairdresser.managers.exception.CustomExceptionModel.CustomForbiddenException;
import com.hairdresser.managers.exception.CustomExceptionModel.CustomInternalServerErrorException;
import com.hairdresser.managers.exception.CustomExceptionModel.CustomNotFoundException;
import com.hairdresser.managers.repository.UserRepository;
import com.hairdresser.managers.service.GrantAdminService;
import com.hairdresser.managers.validation.UserIdValidations;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class GrantAdminServiceImpl implements GrantAdminService {
	
	private final UserRepository userRepositoty;

	@Override
	public void grantAdminService(String userId) {
		UserIdValidations.validateuserId(userId);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		validateAdmin(auth.getAuthorities().toArray());
		var user = getUserFromDb(userId);
		checkActivityAndRole(user);
		grantAdmin(user);
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
	
	private void checkActivityAndRole(UserEntity user) {
		if (!user.getIsActive()) {
			log.error("User is not active");
			throw new CustomBadRequestException("inactive_user");
		}
		if (user.getRole().getRoleId() == 1L) {
			log.error("User is already an Admin");
			throw new CustomBadRequestException("Managers-0009");
		}
	}
	
	private void validateAdmin(Object[] roles) {
		List<String> list =  Arrays.stream(roles)
		        .map(Object::toString)
		        .collect(Collectors.toList());
		if (!list.contains("ADMIN")) {
			log.error("Only an Admin can invalidate an account");
			throw new CustomForbiddenException("not_admin");
		}
	}
	
	private void grantAdmin(UserEntity user) {
		RoleEntity role = new RoleEntity(1L, "ADMIN");
		user.setRole(role);
		try {
			userRepositoty.save(user);
		} catch (DataAccessException e) {
			log.error("There was a problem trying to access the database");
			throw new CustomInternalServerErrorException("database_error");
		}
	}

}
