package com.hairdresser.managers.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class Utils {
	
	private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	public String encodePassword(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

}
