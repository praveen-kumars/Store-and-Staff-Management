package com.authorizationservice.authorization.dto;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ValidatingDTOTest {
	
	@Test
	void validity() {
		ValidatingDTO validatingDTO=new ValidatingDTO(true);
		validatingDTO.setValidStatus(false);
		assertFalse(validatingDTO.isValidStatus());
	}

}
