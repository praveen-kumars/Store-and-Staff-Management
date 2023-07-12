package com.authorizationservice.authorization.Controller;

import org.springframework.http.MediaType;


import javax.xml.stream.events.EndDocument;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.authoizationservice.authorization.Exceptions.LoginException;
import com.authorizationservice.authorization.dto.AuthenticationRequestDTO;
import com.authorizationservice.authorization.dto.ValidatingDTO;
import com.authorizationservice.authorization.model.AuthenticationRequest;
import com.authorizationservice.authorization.model.AuthenticationResponse;
import com.authorizationservice.authorization.service.AuthUserDetailService;
import com.authorizationservice.authorization.util.JwtUtil.JwtUtil;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin
@Slf4j
public class AuthorizationController {
	

	@Autowired
	AuthUserDetailService userDetailsService;
	
	@Autowired
	JwtUtil jwtUtil;
	
	private ValidatingDTO validatingDTO=new ValidatingDTO();
	
	
	@PostMapping(value="/login")
	public ResponseEntity<Object> createAuthorizationToken(@RequestBody AuthenticationRequestDTO authenticationRequestDTO) throws LoginException{
		AuthenticationRequest authenticationRequest=new AuthenticationRequest();
		authenticationRequest.setUsername(authenticationRequestDTO.getUsername());
		authenticationRequest.setPassword(authenticationRequestDTO.getPassword());
		
		log.info("BEGIN - [login(userLoginCredential]");
		final UserDetails userDetails= userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		log.debug("{}",userDetails);
		
		if(userDetails.getPassword().equals(authenticationRequest.getPassword())) {
			log.info("END - [login(userLoginCredential)]");
			return new ResponseEntity<>(new AuthenticationResponse(authenticationRequest.getUsername(),
					jwtUtil.generateToken(userDetails),jwtUtil.getCurrentTime(),jwtUtil.getExpirationTime()),HttpStatus.OK);
					
		}	
		log.info("END - [login(userLoginCredential)]");
		throw new LoginException("Invalid username or password");
	}
	
	@GetMapping(path = "/validate",produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ValidatingDTO> validatingAuthorizationToken(@RequestHeader(name = "Authorization")String tokenDup){
		log.info("BEGIN - [ValidatingAuthorizationToken(JWT-Token)]");
		String token = tokenDup.substring(7);
		try {
			UserDetails user=userDetailsService.loadUserByUsername(jwtUtil.extractUsername(token));
			if(Boolean.TRUE.equals(jwtUtil.validateToken(token, user))) {
				log.debug("Token matched is valid");
				log.info("Token matched is valid");
				log.info("END - Validate()");	
				validatingDTO.setValidStatus(true);
				return new ResponseEntity<>(validatingDTO,HttpStatus.OK);
			}
			else {
				throw new LoginException("Invalid Token");
			}
		}
		catch (Exception e) {
			log.debug("Invalid Token -  Bad Credentials Exception");
			log.info("END -[ValidatingAuthorizationToken(JWT-Token)]");
			
			validatingDTO.setValidStatus(false);
			return new ResponseEntity<>(validatingDTO,HttpStatus.BAD_REQUEST);
		}
	
	}	
	
	@GetMapping(path = "/health-check")
	public ResponseEntity<String> healthcheck(){
		return new ResponseEntity<>("ok",HttpStatus.OK);
	}
}
	


