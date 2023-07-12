package com.managestoreservice.storedata.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="store_request")
public class StoreRequest {
	
	@Id
	@Column(name="shop_id")
	private String store_Id;
	
	@Column(name="request_id")
	private String request_Id;
	
	@Column(name="address")
	private String address;
	
	@Column(name="phone_number")
	private String phone_Number;
	
	@Column(name="area_region_code")
	private String area_Region_Code;
	
	@Column(name="mon_hours")
	private String mon_Hours;
	
	@Column(name="tue_hours")
	private String tue_Hours;
	
	@Column(name="wed_hours")
	private String wed_Hours;
	
	@Column(name="thru_hours")
	private String thru_Hours;
	
	@Column(name="fri_hours")
	private String fri_Hours;
	
	@Column(name="sat_hours")
	private String sat_Hours;
	
	@Column(name="sun_hours")
	private String sun_Hours;
	
	@OneToOne(mappedBy = "storeRequest")
	private Store store;


}
