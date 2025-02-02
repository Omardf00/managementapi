package com.hairdresser.managers.entities;

import java.math.BigInteger;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_detail")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailEntity {
	
	@Id
	private BigInteger id;
	
    private String phoneNumber;

    private String firstLastName;

    private String secondLastName;
    
    @Email
    private String email;

}
