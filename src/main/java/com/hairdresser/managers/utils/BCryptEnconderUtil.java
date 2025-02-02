package com.hairdresser.managers.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class BCryptEnconderUtil {
	
	private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	public static void main(String[] args) {
		System.out.println(encodePassword("Prueba123"));
	}
	
	 public static String encodePassword(String rawPassword) {
	        return passwordEncoder.encode(rawPassword);
	    }

}
