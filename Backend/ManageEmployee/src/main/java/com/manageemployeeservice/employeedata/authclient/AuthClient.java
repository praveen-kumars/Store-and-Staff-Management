package com.manageemployeeservice.employeedata.authclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.manageemployeeservice.employeedata.DTO.ValidatingDTO;

@FeignClient(name="auth-client",url="${Authorization.URL}")
public interface AuthClient {
	
	@GetMapping(value = "/validate")
	public ValidatingDTO getValidity(@RequestHeader(name = "Authorization",required = true) String token);


}
