package com.hairdresser.managers.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.hairdresser.managers.entities.UserEntity;
import com.hairdresser.managers.exception.CustomExceptionModel.CustomUnathorizedException;
import com.hairdresser.managers.model.auth.AuthRequest;
import com.hairdresser.managers.model.auth.AuthResponse;
import com.hairdresser.managers.repository.UserRepository;
import com.hairdresser.managers.validation.LoginValidation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {
	
	private final UserRepository userRepository;
	
	private final AuthenticationManager authenticationManager;
	
	private final JWTService jwtService;
	
	public AuthResponse login(AuthRequest request) {
		LoginValidation.validateEntrydata(request);
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword()));
        var user = userRepository.findByUserName(request.getUserName());
        validateActivity(user);
        var jwtToken = jwtService.generateToken(user);
        AuthResponse authResponse = new AuthResponse();
        authResponse.setToken(jwtToken);
        return authResponse;
    }
	
	private static void validateActivity(UserEntity user) {
		if (user != null && user.getIsActive() == false) {
			log.error("User is not active");
			throw new CustomUnathorizedException("Managers-0001");
		}
	}

}
