package com.managestoreservice.storedata.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class StoreTest {
    Store storesDto=new Store();
	
	@Test
	void notnulltest() {
		assertThat(storesDto).isNotNull();
	}
	
	@Test
	void StoreDtoTest() {
		Store storeDto=new Store("76bc9","G14-11 th street","Los Angles, California, 12343","8220551256","9am-7pm","9am-7pm","9am-7pm","9am-7pm","9am-7pm","","",null);
		storeDto.setStore_Id("8bc74");
		storeDto.setAddress("A-6");
		storeDto.setPhone_Number("1111134568");
		storeDto.setArea_Region_Code("california,US");
		storeDto.setMon_Hours("8am-9pm");
		storeDto.setTue_Hours("8am-9pm");
		storeDto.setWed_Hours("8am-9pm");
		storeDto.setThru_Hours("8am-9pm");
		storeDto.setFri_Hours("8am-9pm");
		storeDto.setSat_Hours("8am-9pm");
		storeDto.setSun_Hours("8am-9pm");
		storeDto.setStoreRequest(null);
		assertEquals("8bc74",storeDto.getStore_Id());
		assertEquals("1111134568",storeDto.getPhone_Number());
		assertEquals("california,US", storeDto.getArea_Region_Code());
		assertEquals("A-6",storeDto.getAddress());
		assertEquals("8am-9pm",storeDto.getMon_Hours());
		assertEquals("8am-9pm",storeDto.getTue_Hours());
		assertEquals("8am-9pm",storeDto.getWed_Hours());
		assertEquals("8am-9pm",storeDto.getThru_Hours());
		assertEquals("8am-9pm",storeDto.getFri_Hours());
		assertEquals("8am-9pm",storeDto.getSat_Hours());
		assertEquals("8am-9pm",storeDto.getSun_Hours());

		assertEquals(null,storeDto.getStoreRequest());

}}
