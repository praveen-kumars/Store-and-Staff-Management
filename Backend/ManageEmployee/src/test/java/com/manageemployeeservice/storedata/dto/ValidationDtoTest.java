package com.manageemployeeservice.storedata.dto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.manageemployeeservice.employeedata.DTO.ValidatingDTO;

public class ValidationDtoTest {
	
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
