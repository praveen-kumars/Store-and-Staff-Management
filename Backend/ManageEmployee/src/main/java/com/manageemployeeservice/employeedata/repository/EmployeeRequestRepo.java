package com.manageemployeeservice.employeedata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.manageemployeeservice.employeedata.model.EmployeeRequest;

public interface EmployeeRequestRepo extends JpaRepository<EmployeeRequest,String>
{
	@Query(value = "select * from employee_request s where s.request_id=:n",nativeQuery=true)
	public EmployeeRequest findByrequest_Id(@Param("n") String requestId);


}
