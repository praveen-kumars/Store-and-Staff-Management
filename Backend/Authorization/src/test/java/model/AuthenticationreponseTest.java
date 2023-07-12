package model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.authorizationservice.authorization.AuthorizationApplication;
import com.authorizationservice.authorization.model.AuthenticationResponse;

@SpringBootTest
@ContextConfiguration(classes =AuthorizationApplication.class )
public class AuthenticationreponseTest {
	
	AuthenticationResponse authenticationResponse=new AuthenticationResponse();
	
	@Test
	void notnulltest() {
		assertThat(authenticationResponse).isNotNull();
		
	}
	
	@Test
	void authenticationresponsetest() {
		AuthenticationResponse authenticationResponse=new AuthenticationResponse("Springboot","wertyuiop",10l,11l);
		authenticationResponse.setJwtAuthTokenString("qwerty");
		authenticationResponse.setUsername("Arun");
		authenticationResponse.setTokenExpirationTime(11l);
		authenticationResponse.setServerCurrentTime(34l);
		assertEquals("qwerty",authenticationResponse.getJwtAuthTokenString());
		assertEquals("Arun", authenticationResponse.getUsername());
		assertEquals(11l,authenticationResponse.getTokenExpirationTime());
		assertEquals(34l,authenticationResponse.getServerCurrentTime());
	}

}
