package com.manageemployeeservice.storedata.controllerTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.manageemployeeservice.employeedata.ManageEmployeeApplication;
import com.manageemployeeservice.employeedata.DTO.ValidatingDTO;
import com.manageemployeeservice.employeedata.authclient.AuthClient;
import com.manageemployeeservice.employeedata.controller.UpdateEmployeeController;
import com.manageemployeeservice.employeedata.exception.InvalidTokenException;
import com.manageemployeeservice.employeedata.model.Employee;
import com.manageemployeeservice.employeedata.model.EmployeeRequest;
import com.manageemployeeservice.employeedata.service.UpdateService;

@SpringBootTest(classes = ManageEmployeeApplication.class)
public class EmployeeControllerTest {
	
	@InjectMocks
	UpdateEmployeeController updateEmployeeController;
	
	@Mock
	AuthClient authClient;
	
	@Mock
	UpdateService updateService;
	
	String tokenString="AAA";
	String employeeId="12543";
	Employee employee=new Employee("12543","Manager","Test_12543@abc.com","113897234872","3298729872","Medic, los Angles, California",null);
	EmployeeRequest employeeRequest=new EmployeeRequest("12543","REQ-12543","Manager","Test_12543@abc.com","113897234872","3298729872","Medic, los Angles, California",null);
	
	
	@Test
	void EmployeeTestControllerNotNullTest() {
		UpdateEmployeeController updateEmployeeController=new UpdateEmployeeController();
		assertThat(updateEmployeeController).isNotNull();
	}
	
	@Test
	void retrieveEmployeeTest() {
		ValidatingDTO validatingDTO=new ValidatingDTO(true);
		when(authClient.getValidity(tokenString)).thenReturn(validatingDTO);
		when(updateService.getEmployeeDetails(employee.getStaff_Id())).thenReturn(new ResponseEntity<Employee>(employee,HttpStatus.OK));
		assertEquals(employee.getStaff_Id(),updateEmployeeController.retrieveemployee(employee.getStaff_Id(),tokenString).getBody().getStaff_Id());
	}
	
	@Test
	void invalidtokenretrieveEmployee() {
		ValidatingDTO validatingDTO=new ValidatingDTO(false);
		when(authClient.getValidity(tokenString)).thenReturn(validatingDTO);
		assertThrows(InvalidTokenException.class, ()->
		updateEmployeeController.retrieveemployee(employeeId, tokenString));
	}
	
	@Test
	void addStoreTest() {
	ValidatingDTO validatingDTO=new ValidatingDTO(true);
		when(authClient.getValidity(tokenString)).thenReturn(validatingDTO);
		when(updateService.saveEmployee(employeeRequest, tokenString)).thenReturn(new ResponseEntity<>(employeeRequest,HttpStatus.OK));
		assertEquals(employeeRequest.getStaff_Id(),updateEmployeeController.addEmployee(employeeRequest,tokenString).getBody().getStaff_Id());	
	}
	
	@Test
	void invalidtokenaddEmployee() {		
		ValidatingDTO validatingDTO=new ValidatingDTO(false);
		when(authClient.getValidity(tokenString)).thenReturn(validatingDTO);
		assertThrows(InvalidTokenException.class, ()->
		updateEmployeeController.addEmployee(employeeRequest, tokenString));
	}
	
	@Test
	void updateEmployeeTest() {
		when(updateService.updateEmployeeDetails(employeeRequest.getRequest_Id())).thenReturn(new ResponseEntity<>(employeeRequest,HttpStatus.OK));
		assertEquals(HttpStatus.OK,updateEmployeeController.updateEmployee(employeeRequest.getRequest_Id()).getStatusCode());
	}
	
	@Test
	void retrieverequestDataTest() {
		ValidatingDTO validatingDTO=new ValidatingDTO(true);
		when(authClient.getValidity(tokenString)).thenReturn(validatingDTO);
		//when(updateService.retrieveMatching()).thenReturn(new ResponseEntity<List<Employee>>(HttpStatus.OK));
	//	when(updateEmployeeController.retrieverequestData(tokenString)).thenReturn(ResponseEntity<List<EmployeeRequest>>.);
		//assertEquals(HttpStatus.OK,updateEmployeeController.retrieverequestData(tokenString).getStatusCode());	
	}
	@Test
	void invalidtokenretrieveData() {		
		ValidatingDTO validatingDTO=new ValidatingDTO(false);
		when(authClient.getValidity(tokenString)).thenReturn(validatingDTO);
		assertThrows(InvalidTokenException.class, ()->
		updateEmployeeController.retrieverequestData(tokenString));
	}
	
	@Test
	void retrievematchingDataTest() {
		ValidatingDTO validatingDTO=new ValidatingDTO(true);
		when(authClient.getValidity(tokenString)).thenReturn(validatingDTO);
		when(updateService.retrieveMatching()).thenReturn(new ResponseEntity<List<Employee>>(HttpStatus.OK));
		assertEquals(HttpStatus.OK, updateEmployeeController.retrievematchingData(tokenString).getStatusCode());
	}
	
	@Test
	void invalidtokenretrieveMatchingData() {		
		ValidatingDTO validatingDTO=new ValidatingDTO(false);
		when(authClient.getValidity(tokenString)).thenReturn(validatingDTO);
		assertThrows(InvalidTokenException.class, ()->
		updateEmployeeController.retrievematchingData(tokenString));
	}
	
	@Test
	void deleteRequestTest() {
		when(updateService.deleteRequest(employee.getStaff_Id())).thenReturn(new ResponseEntity<>(HttpStatus.OK));
		assertEquals(new ResponseEntity<>(HttpStatus.OK),updateEmployeeController.deleteRequest(employee.getStaff_Id()));		
	}
	
	@Test
	void updateTest() {
		
		
	}
	

}
