package com.manageemployeeservice.employeedata.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.manageemployeeservice.employeedata.model.Employee;
import com.manageemployeeservice.employeedata.model.EmployeeRequest;
import com.manageemployeeservice.employeedata.repository.EmployeeRepo;
import com.manageemployeeservice.employeedata.repository.EmployeeRequestRepo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UpdateService {
	
	@Autowired
	EmployeeRepo employeeRepo;
	
	@Autowired
	EmployeeRequestRepo employeeRequestRepo;

	public UpdateService(EmployeeRequestRepo employeeRequestRepomock) {
	
		this.employeeRequestRepo=employeeRequestRepomock;
		
	}

	

	public ResponseEntity<Employee> getEmployeeDetails(String employeeName) {
		log.info("BEGIN -[Get Employee details]");
		Employee employeedetail=employeeRepo.findById(employeeName).get();
		if(employeeRequestRepo.existsById(employeeName)){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);	
		}
		else {
			log.info("END-[Employee details found]");
			return new ResponseEntity<>(employeedetail,HttpStatus.OK);	
		}
		
	}

	public ResponseEntity<EmployeeRequest> saveEmployee(EmployeeRequest employeeRequest, String token) {
		log.info("BEGIN - [Save request employee data]");

		if(employeeRequestRepo.existsById(employeeRequest.getStaff_Id())) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		else {
			employeeRequest.setRequest_Id("REQ-"+employeeRequest.getStaff_Id());
			employeeRequestRepo.save(employeeRequest);
			log.info("END-[Request data saved]");
			return new ResponseEntity<>(employeeRequest,HttpStatus.OK);
			
		}	
	}

	public ResponseEntity updateEmployeeDetails(String requestId) {
		log.info("BEGIN - [Update employee details]");
		EmployeeRequest employeedetailRequest=employeeRequestRepo.findByrequest_Id(requestId);
		Employee employee=employeeRepo.findById(employeedetailRequest.getStaff_Id()).get();
		employee.setDept_Area_Region_District(employeedetailRequest.getDept_Area_Region_District());
		employee.setEmail(employeedetailRequest.getEmail());
		employee.setOffice_Phone_Num(employeedetailRequest.getOffice_Phone_Num());
		employee.setPhone_Number(employeedetailRequest.getPhone_Number());
		employee.setPosition(employeedetailRequest.getPosition());
	    employeeRepo.save(employee);
	    employeeRequestRepo.deleteById(employeedetailRequest.getStaff_Id());
		log.info("END - [Details succesfully updated]");
		
	    return new ResponseEntity<>(HttpStatus.OK);
	}

	public ResponseEntity<List<Employee>> retrieveMatching() {
		return new ResponseEntity<>(employeeRepo.findByrequest_Id(),HttpStatus.OK);
	}

	public ResponseEntity deleteRequest(String employee_Id) {
		log.info("BEGIN -[Delete request details by id]");
		
		employeeRequestRepo.deleteById(employee_Id);	
		log.info("END - [Employee request deleted]");
		return new ResponseEntity<>(HttpStatus.OK);
	}


}
