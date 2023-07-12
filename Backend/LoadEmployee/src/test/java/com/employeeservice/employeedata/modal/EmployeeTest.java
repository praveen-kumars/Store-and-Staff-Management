package com.employeeservice.employeedata.modal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.employeeservice.employeedata.model.Employee;

public class EmployeeTest {
	
	Employee employee=new Employee();
	
	@Test
    void employeeNotNull() {
		assertThat(employee).isNotNull();
	}
	
	@Test
	void employeeTest() {
		Employee employee=new Employee("12435","Manager","test123@gmil.com","123464324","822044563","Califprnia");
		employee.setStaff_Id("23456");
		employee.setPosition("Supervisor");
		employee.setOffice_Phone_Num("3245678921");
		employee.setPhone_Number("9432345930");
		employee.setDept_Area_Region_District("Us florida");
		employee.setEmail("Test23456@gmail.com");
		
		assertEquals("23456",employee.getStaff_Id());
		assertEquals("Supervisor",employee.getPosition());
		assertEquals("3245678921",employee.getOffice_Phone_Num());
		assertEquals("9432345930",employee.getPhone_Number());
		assertEquals("Us florida",employee.getDept_Area_Region_District());
		assertEquals("Test23456@gmail.com",employee.getEmail());
		
		
		
	}
}
