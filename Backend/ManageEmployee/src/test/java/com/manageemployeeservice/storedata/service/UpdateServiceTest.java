package com.manageemployeeservice.storedata.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

import com.manageemployeeservice.employeedata.ManageEmployeeApplication;
import com.manageemployeeservice.employeedata.model.Employee;
import com.manageemployeeservice.employeedata.model.EmployeeRequest;
import com.manageemployeeservice.employeedata.repository.EmployeeRepo;
import com.manageemployeeservice.employeedata.repository.EmployeeRequestRepo;
import com.manageemployeeservice.employeedata.service.UpdateService;

@SpringBootTest(classes =ManageEmployeeApplication.class)
@ContextConfiguration(classes =ManageEmployeeApplication.class)
public class UpdateServiceTest {
	
	
	@Autowired
	private UpdateService updateService;
	
	@MockBean
	EmployeeRepo employeeRepo;
	
	@MockBean
	EmployeeRequest employeeRequestRepo;
	
	
	@Test
	void getEmployeeDetailsService() {
		assertThat(updateService).isNotNull();
	}
	
	
	@Test
	void getEmployeeTest() {
		String Staff_Id="95242";
		Employee employee=new Employee("95242","Manager","Test_12543@abc.com","113897234872","3298729872","Medic, los Angles, California",null);
		when(employeeRepo.findById(Staff_Id)).thenReturn(Optional.of(employee));
		assertEquals(employee.getStaff_Id(),updateService.getEmployeeDetails(Staff_Id).getBody().getStaff_Id());
	}
	
	
	/*@Test
	void saveRequest(){
		EmployeeRequest employeeRequest=new EmployeeRequest("95242",null,"Manager","Test_12543@abc.com","113897234872","3298729872","Medic, los Angles, California",null);
		String tokenString="AAA";
		assertEquals(employeeRequest.getStaff_Id(),updateService.saveEmployee(employeeRequest,tokenString).getBody().getStaff_Id());

	}*/
	
	@Test
	void updateemployeeTest() {
		EmployeeRepo employeeRepomock=mock(EmployeeRepo.class);
		EmployeeRequestRepo employeeRequestRepomock=mock(EmployeeRequestRepo.class);
		EmployeeRequest employeeRequest=new EmployeeRequest("95242","REQ-95242","Manager","Test_12543@abc.com","113897234872","3298729872","Medic, los Angles, California",null);
		Employee employee=new Employee("95242","Manager","Test_12543@abc.com","113897234872","3298729872","Medic, los Angles, California",null);
		when(employeeRequestRepomock.findByrequest_Id("REQ-95242")).thenReturn(employeeRequest);
	   // when(employeeRepomock.findById(employeeRequest.getStaff_Id()).get()).thenReturn(employee);
	//	UpdateService updateService=new UpdateService(employeeRequestRepomock);
		
	//	ResponseEntity<?> returnEmployeeRequest=updateService.updateEmployeeDetails("95242");	
	  //  assertEquals(new ResponseEntity<>(HttpStatus.OK),updateService.updateEmployeeDetails("95242"));
	}
	
	
	
	
	@Test
	void testforSaveRequest() {
		EmployeeRequestRepo employeeRequestRepomock=mock(EmployeeRequestRepo.class);
		EmployeeRequest employeeRequest=new EmployeeRequest();
		employeeRequest.setRequest_Id("REQ21162");
		employeeRequest.setStaff_Id("21162");
		employeeRequest.setPosition("Manager");
		employeeRequest.setEmail("Test_21162@gmail.com");
		employeeRequest.setOffice_Phone_Num("+11 3897234872");
		employeeRequest.setPhone_Number("8220551298");
		employeeRequest.setDept_Area_Region_District("California");
		UpdateService updateService=new UpdateService(employeeRequestRepomock);
		String tokenString="AAA";
		ResponseEntity<EmployeeRequest> returnEmployeeRequest=updateService.saveEmployee(employeeRequest, tokenString);	
		assertEquals(employeeRequest.getStaff_Id(),returnEmployeeRequest.getBody().getStaff_Id());
	}
	
	@Test
	void retrieveMatching() {
		assertEquals(new ResponseEntity<>(employeeRepo.findByrequest_Id(),HttpStatus.OK),updateService.retrieveMatching());
	}
	
	@Test
	void Deleterequesttest() {
		EmployeeRequestRepo employeeRequestRepomock=mock(EmployeeRequestRepo.class);
		UpdateService updateService=new UpdateService(employeeRequestRepomock);
		ResponseEntity<EmployeeRequest> returnEmployeeRequest=updateService.deleteRequest("95242");
		assertEquals(HttpStatus.OK,returnEmployeeRequest.getStatusCode());
	
		
	}
	
	
	

}
