package com.hairdresser.managers.validation;

import com.hairdresser.managers.exception.CustomExceptionModel.CustomBadRequestException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserIdValidations {
	
	public static void validateuserId(String userId) {
		try {
			Long.parseLong(userId);
		} catch (NumberFormatException e) {
			log.error("Invalid userId");
			throw new CustomBadRequestException("Managers-0003");
		}
	}

}
