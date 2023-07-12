package model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.authorizationservice.authorization.AuthorizationApplication;
import com.authorizationservice.authorization.model.AuthenticationRequest;

@SpringBootTest
@ContextConfiguration(classes =AuthorizationApplication.class )
public class AuthenticationrequestTest {
	
	@Mock
	private AuthenticationRequest authenticationRequest;
	
	@Test
	void authenticationrequestnotnulltest() {
		assertThat(authenticationRequest).isNotNull();
	}
	
	@Test
	void testuserLoginallargs() {
		AuthenticationRequest authenticationRequest=new AuthenticationRequest("xxxxxx@gmail.com","Springboot@123");
		assertEquals("xxxxxx@gmail.com", authenticationRequest.getUsername());
		assertEquals("Springboot@123", authenticationRequest.getPassword());
		
	}
	
	@Test
	void testuserlogin() {
		AuthenticationRequest authenticationRequest=new AuthenticationRequest("xxxxxx@gmail.com","Springboot@123");
		authenticationRequest.setUsername("Praveen");
		authenticationRequest.setPassword("Arun@2003");
		assertEquals("Praveen",authenticationRequest.getUsername());
		assertEquals("Arun@2003",authenticationRequest.getPassword());
	}

}
