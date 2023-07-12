package com.authorizationservice.authorization.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponse {
	
	

	private String username;
	
	private String jwtAuthTokenString;
	
	private long serverCurrentTime;
	
	private long tokenExpirationTime;

	

}
