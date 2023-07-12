package com.manageemployeeservice.employeedata.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.manageemployeeservice.employeedata.model.Employee;

public interface EmployeeRepo extends JpaRepository<Employee,String> {
	@Query(value = "select * from master_employee_data inner join employee_request on master_employee_data.staff_id=employee_request.staff_id",nativeQuery=true)
	List<Employee> findByrequest_Id();

}
