package com.manageemployeeservice.storedata.modal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.manageemployeeservice.employeedata.model.Employee;
import com.manageemployeeservice.employeedata.model.EmployeeRequest;

public class EmployeeRequestTest {
	
	EmployeeRequest employee=new EmployeeRequest();

	@Test
	void notnulltest() {
		assertThat(employee).isNotNull();
	}
	
	@Test
	void Employeetest() {
		EmployeeRequest employee=new EmployeeRequest("12543","REQ-12543","Manager","Test_12543@abc.com","113897234872","3298729872","Medic, los Angles, California",null);
        employee.setStaff_Id("23456");
        employee.setPosition("Supervisor");
        employee.setOffice_Phone_Num("1111453235");
        employee.setPhone_Number("9534389430");
        employee.setDept_Area_Region_District("california,US");
        employee.setEmail("Test23456@gmail.com");
        employee.setRequest_Id("REQ-23456");
        employee.setEmployee(null);
        assertEquals("23456", employee.getStaff_Id());
        assertEquals("Supervisor", employee.getPosition());
        assertEquals("1111453235", employee.getOffice_Phone_Num());
        assertEquals("9534389430", employee.getPhone_Number());
        assertEquals("california,US", employee.getDept_Area_Region_District());
        assertEquals("Test23456@gmail.com", employee.getEmail());
	    assertEquals("REQ-23456", employee.getRequest_Id());
	    assertEquals(null,employee.getEmployee());
	}

}
