package com.managestoreservice.storedata.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StoreDTO {
	
	private String store_Id;
	
	
	private String address;
	
	
	private String phone_Number;
	
	
	private String area_Region_Code;
	
	
	private String mon_Hours;
	
	
	private String tue_Hours;
	
	
	private String wed_Hours;
	
	
	private String thru_Hours;
	
	
	private String fri_Hours;
	
	
	private String sat_Hours;
	
	
	private String sun_Hours;

}
