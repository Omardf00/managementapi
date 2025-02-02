package com.hairdresser.managers.model;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Details {

	private String phoneNumber;

	private String realName;

	private String firstLastName;

	private String secondLastName;

	@Email
	private String email;

}
