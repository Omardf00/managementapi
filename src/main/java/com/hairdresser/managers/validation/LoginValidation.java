package com.hairdresser.managers.validation;

import com.hairdresser.managers.exception.CustomExceptionModel.CustomUnathorizedException;
import com.hairdresser.managers.model.auth.AuthRequest;

import io.micrometer.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginValidation {
	
	public static void validateEntrydata(AuthRequest data) {
		if (StringUtils.isEmpty(data.getUserName()) || StringUtils.isEmpty(data.getPassword())) {
			log.info("Missing information in login request");
			throw new CustomUnathorizedException("unauthorized");
		}
	}

}
