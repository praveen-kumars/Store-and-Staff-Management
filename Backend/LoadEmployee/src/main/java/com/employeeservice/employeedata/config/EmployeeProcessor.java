package com.employeeservice.employeedata.config;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hibernate.dialect.function.StaticPrecisionFspTimestampFunction;
import org.springframework.batch.item.ItemProcessor;

import com.employeeservice.employeedata.model.Employee;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class EmployeeProcessor implements ItemProcessor<Employee, Employee>{

	@Override
	public Employee process(Employee employee) throws Exception {
	
		log.info("BEGIN - [Validating Employee-Id & Phone Number]");
		
		if(staffValidation(employee.getStaff_Id()) && staffPhoneNumValidation(employee.getPhone_Number())){
			log.info("END - [Validation completed]");
			
			return employee;		
		}
		else {
			log.info("END - [Condition not met]");
			return null;
		}
	}

	private boolean staffValidation(String staff_Id) {
		log.info("BEGIN - [Staff Id validation]");
		
		if(staff_Id.length()<5) {
			return false;
		}
		Pattern patternForNumber=Pattern.compile("^[0-9]*$");
		Matcher matcherForNumber=patternForNumber.matcher(staff_Id);
	    boolean isStringContainsNumber=matcherForNumber.find();
	    
	    Pattern patternForNotOnlyZero=Pattern.compile("\\d*[1-9]\\d*");
	    Matcher matcherForNotOnlyZero=patternForNotOnlyZero.matcher(staff_Id);
	    boolean isStringContainsNotOnlyZero=matcherForNotOnlyZero.find();
	    
	    if(isStringContainsNumber&&isStringContainsNotOnlyZero) {
	    	log.info("END - Staff Id validation]");
	    	return true;
	    }
	    else {
	    	return false;
	    }
	}

	private boolean staffPhoneNumValidation(String phone_Number) {
		log.info("BEGIN - [phone number validation]");
		if(phone_Number.length()<10) {
			return false;
		}
		Pattern patterForPhoneNumberPattern=Pattern.compile("\\(?\\d{3}\\)?-? *\\d{3}-? *-?\\d{4}");
		Matcher matcherForPhoneNumberMatcher=patterForPhoneNumberPattern.matcher(phone_Number);
		boolean isStringContainsCellPhoneNumber=matcherForPhoneNumberMatcher.find();
		
		if(isStringContainsCellPhoneNumber) {
			log.info("END - phone validation]");
			return true;
			
		}
		else {
			return false;
		}
		
	}
	
	
	
	
	
	
}