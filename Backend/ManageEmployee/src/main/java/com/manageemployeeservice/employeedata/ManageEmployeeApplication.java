package com.manageemployeeservice.employeedata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ManageEmployeeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManageEmployeeApplication.class, args);
	}

}
