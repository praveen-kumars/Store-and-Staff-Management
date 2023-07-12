package com.authorizationservice.authorization.dto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.authorizationservice.authorization.model.AuthenticationRequest;

@SpringBootTest
public class AuthenticationRequestdtoTest {
	
	AuthenticationRequestDTO authenticationRequest=new AuthenticationRequestDTO();
	
	@Test
	void authenticationrequestnotnulltest() {
		assertThat(authenticationRequest).isNotNull();
	}
	
	@Test
	void testuserLoginallargs() {
		AuthenticationRequestDTO authenticationRequest=new AuthenticationRequestDTO("xxxxxx@gmail.com","Springboot@123");
		authenticationRequest.setUsername("xxxxxx@gmail.com");
		authenticationRequest.setPassword("Springboot@123");
		assertEquals("xxxxxx@gmail.com", authenticationRequest.getUsername());
		assertEquals("Springboot@123", authenticationRequest.getPassword());
		
	}
	
	@Test
	void testuserlogin() {
		AuthenticationRequestDTO authenticationRequest=new AuthenticationRequestDTO("xxxxxx@gmail.com","Springboot@123");
		authenticationRequest.setUsername("Praveen");
		authenticationRequest.setPassword("Arun@2003");
		assertEquals("Praveen",authenticationRequest.getUsername());
		assertEquals("Arun@2003",authenticationRequest.getPassword());
	}

}
