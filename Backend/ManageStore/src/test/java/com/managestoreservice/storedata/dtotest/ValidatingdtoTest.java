package com.managestoreservice.storedata.dtotest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.managestoreservice.storedata.DTO.ValidatingDTO;

public class ValidatingdtoTest {
	
	ValidatingDTO validatingDTO=new ValidatingDTO();
	@Test
	void notnulltest() {
		assertThat(validatingDTO).isNotNull();
	}
	
	@Test
	void validatingDtoTest() {
	      ValidatingDTO validatingDTO=new ValidatingDTO(true);
	      validatingDTO.setValidStatus(false);
	      assertEquals(false,validatingDTO.isValidStatus());
	}
	
}
