package com.hairdresser.managers.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class Utils {
	
	private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	@Value("${app.config.key}")
	private String key;
	
	public String getKey() {
		return key != null ? key : null;
	}
	
	public String encodePassword(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

}
