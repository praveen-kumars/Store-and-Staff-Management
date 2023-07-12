package com.employeeservice.employeedata.config;




import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.employeeservice.employeedata.model.Employee;

@SpringBootTest
public class ProcessorTest {
	Employee employee=new Employee("12543","Manager","test123@gmil.com","113897234872","3298729872","Califprnia");
	Employee employeenull=new Employee("12543dwd","Manager","test123@gmil.com","113897234872","3298729872","Califprnia");
	
	
	
	
	@Autowired
	EmployeeProcessor employeeProcessor;
	
	@Test 
	void validateemployeeIdTest() throws Exception{
		assertEquals(employee,employeeProcessor.process(employee));
	}
	@Test 
	void validateemployeeIdnullTest() throws Exception{
		assertEquals(null,employeeProcessor.process(employeenull));
	}

}
