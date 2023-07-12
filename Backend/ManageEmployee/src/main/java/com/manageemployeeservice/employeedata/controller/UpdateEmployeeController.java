package com.manageemployeeservice.employeedata.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.manageemployeeservice.employeedata.authclient.AuthClient;
import com.manageemployeeservice.employeedata.exception.InvalidTokenException;
import com.manageemployeeservice.employeedata.model.Employee;
import com.manageemployeeservice.employeedata.model.EmployeeRequest;
import com.manageemployeeservice.employeedata.repository.EmployeeRequestRepo;
import com.manageemployeeservice.employeedata.service.UpdateService;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin(origins={"*"})
@Slf4j
public class UpdateEmployeeController {
	
	@Autowired
	private AuthClient authClient;
	
	@Autowired 
	private UpdateService updateService;
	
	@Autowired
	private EmployeeRequestRepo employeeRequestRepo;
	
	@GetMapping(path = "/retrieve/{employeeName}")
	public ResponseEntity<Employee> retrieveemployee(@PathVariable("employeeName") String employeeName,@RequestHeader(name="Authorization",required = true) String token)
	throws InvalidTokenException{
		log.info("BEGIN - [Retrieve Employee Details]");
		log.info("BEGIN -[Token validity Check]");
		
	
	if(!authClient.getValidity(token).isValidStatus()) {
		throw new InvalidTokenException("Token is either expired or invalid");
		//return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	log.info("END - [Data Retrieved]");
	return updateService.getEmployeeDetails(employeeName);
	}
	
	@PostMapping(path = "/add-employee")
	public ResponseEntity<EmployeeRequest> addEmployee(@RequestBody EmployeeRequest employeeRequest,@RequestHeader(name="Authorization",required = true) String token)
	throws InvalidTokenException,NullPointerException{
		log.info("BEGIN -[Adding employee request]");
		
		if(!authClient.getValidity(token).isValidStatus()) {
			throw new InvalidTokenException("Token is either invalid");
	}
		System.out.println(employeeRequest);
		log.info("END -[Employee request added]");

		return updateService.saveEmployee(employeeRequest, token);
	}
	
	@PutMapping(path = "/updateemployee/{requestId}")
	public ResponseEntity updateEmployee(@PathVariable String requestId){
		log.info("BEGIN - [Update Data]");
		log.info("END -[Data Updated]");
		return updateService.updateEmployeeDetails(requestId);
	}
	
	@GetMapping(path = "/retrieveall")
	public ResponseEntity<List<EmployeeRequest>> retrieverequestData(@RequestHeader(name="Authorization",required = true) String token)
	throws InvalidTokenException{
		log.info("BEGIN - [Retrieve all request data]");
		
	
	if(!authClient.getValidity(token).isValidStatus()) {
		throw new InvalidTokenException("Token is either expired or invalid");
		//return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	log.info("Request table retrieved]");

	return ResponseEntity.ok(employeeRequestRepo.findAll());
	}
	
	@GetMapping(path = "/retrievematch")
	public ResponseEntity<List<Employee>> retrievematchingData(@RequestHeader(name="Authorization",required = true) String token)
	throws InvalidTokenException{
		
		if(!authClient.getValidity(token).isValidStatus()) {
			throw new InvalidTokenException("Token is either expired or invalid");
			//return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		log.info("BEGIN - Matching data in Employee table]");
		log.info("END -[Matching data retrieved]");
	
    return updateService.retrieveMatching();
	
	}
	@DeleteMapping(path = "/deleterequest/{Employee_Id}")
	public ResponseEntity deleteRequest(@PathVariable String Employee_Id){
		log.info("BEGIN -[Delete Employee request]");
		log.info("END- [Store request deleted]");
		return updateService.deleteRequest(Employee_Id);
	}
	
	

}
