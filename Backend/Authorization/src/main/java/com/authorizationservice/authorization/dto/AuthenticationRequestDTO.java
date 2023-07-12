package com.authorizationservice.authorization.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class AuthenticationRequestDTO {
	
	public String username;
	public String password;

}
