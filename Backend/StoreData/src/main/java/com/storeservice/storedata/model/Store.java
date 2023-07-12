package com.storeservice.storedata.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.batch.item.file.transform.FieldSet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="master_store_data")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Store {
	
	public static String[] Fields() {
		return new String[] {"store_Id","address","phone_Number","area_Region_Code","mon_Hours","tue_Hours",
			"wed_Hours","thru_Hours","fri_Hours","sat_Hours","sun_Hours"};}
	
	
	@Id
	@Column(name="shop_id")
	private String store_Id;
	
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
	
	

}
