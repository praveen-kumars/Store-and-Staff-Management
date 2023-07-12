package com.employeeservice.employeedata.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employeeservice.employeedata.model.Employee;

public interface EmployeeRepo extends JpaRepository<Employee,String>{

}
