package com.employeeservice.employeedata.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="master_employee_Data")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
	
	
	@Id
	@Column(name="staff_id")
	private String staff_Id;
	
	@Column(name="position")
	private String position;
	
	@Column(name="email")
	private String email;
	
	@Column(name="office_phone_num")
	private String office_Phone_Num;
	
	@Column(name="phone_number")
	private String phone_Number;
	
	@Column(name="dept_area_region_district")
	private String dept_Area_Region_District;

}
