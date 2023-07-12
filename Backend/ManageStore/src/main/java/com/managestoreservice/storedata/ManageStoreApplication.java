package com.managestoreservice.storedata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ManageStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManageStoreApplication.class, args);
	}

}
