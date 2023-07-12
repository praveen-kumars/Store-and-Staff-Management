package com.manageemployeeservice.storedata;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.manageemployeeservice.employeedata.ManageEmployeeApplication;

@SpringBootTest(classes = ManageEmployeeApplication.class)
class ManageEmployeeApplicationTests {

	@Test
	void contextLoads() {
		ManageEmployeeApplication.main(new String[] {});
	}

}
