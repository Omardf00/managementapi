package com.hairdresser.managers.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Utils {
	
	@Value("${app.config.key}")
	private String key;
	
	public String getKey() {
		return key != null ? key : null;
	}

}
