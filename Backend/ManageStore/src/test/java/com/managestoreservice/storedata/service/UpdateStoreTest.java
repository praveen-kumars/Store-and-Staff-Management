package com.managestoreservice.storedata.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.managestoreservice.storedata.model.Store;
import com.managestoreservice.storedata.model.StoreRequest;
import com.managestoreservice.storedata.repository.StoreRepo;
import com.managestoreservice.storedata.repository.StoreRequestRepo;

@SpringBootTest
public class UpdateStoreTest {
	
	@Autowired
	private UpdateService updateService;
	
	@MockBean
	StoreRepo storeRepo;
	
	@MockBean
	StoreRequestRepo storeRequestRepo;
	
	String storeName="34563";
	
	@Test
	void getStoreDetailsService() {
		assertThat(updateService).isNotNull();
	}
	
	@Test
	void getStore() {
		String StoreName="76bc9";
		
		Store store=new Store("76bc9","G14-11 th street","Los Angles, California, 12343","8220551256","9am-7pm","9am-7pm","9am-7pm","9am-7pm","9am-7pm","","",null);
		when(storeRepo.findById(StoreName)).thenReturn(Optional.of(store));
		System.out.println(store);
		assertEquals(store.getStore_Id(),updateService.getStoreDetails(StoreName).getBody().getStore_Id());
		
	
	}
	
	@Test
	void Deleterequesttest() {
		StoreRequestRepo storeRequestrepomock=mock(StoreRequestRepo.class);
		StoreRequest storeRequest=new StoreRequest("76bc9","G14-11 th street","Los Angles, California, 12343","8220551256","9am-7pm","9am-7pm","9am-7pm","9am-7pm","9am-7pm","","",null, null);
		UpdateService updateService=new UpdateService(storeRequestrepomock);
		ResponseEntity<StoreRequest> returnStoreRequest=updateService.deleteRequest("95242");
		assertEquals(HttpStatus.OK,returnStoreRequest.getStatusCode());
	}
	
	
	
	
	@Test
	void saveRequest() {
		String tokenString="AAA";
		StoreRequest storeRequest=new StoreRequest("76bc9","G14-11 th street","Los Angles, California, 12343","8220551256","9am-7pm","9am-7pm","9am-7pm","9am-7pm","9am-7pm","","",null, null);
		assertEquals(storeRequest.getStore_Id(),updateService.saveStore(storeRequest,tokenString).getBody().getStore_Id());

	}
	
	@Test
	void updateemployeeTest() {
		StoreRepo employeeRepomock=mock(StoreRepo.class);
		StoreRequestRepo employeeRequestRepomock=mock(StoreRequestRepo.class);
		StoreRequest storeRequest=new StoreRequest("76bc9","G14-11 th street","Los Angles, California, 12343","8220551256","9am-7pm","9am-7pm","9am-7pm","9am-7pm","9am-7pm","","",null, null);
		Store store=new Store("76bc9","G14-11 th street","Los Angles, California, 12343","8220551256","9am-7pm","9am-7pm","9am-7pm","9am-7pm","9am-7pm","","",null);
		when(employeeRequestRepomock.findByrequest_Id("REQ-95242")).thenReturn(storeRequest);
	//  when(employeeRepomock.findById(storeRequest.getStore_Id()).get()).thenReturn(store);
	//   UpdateService updateService=new UpdateService(employeeRequestRepomock);
		
	//ResponseEntity<?> returnEmployeeRequest=updateService.updateStoreDetails("REQ-76bc9");	
	  // assertEquals(new ResponseEntity<>(HttpStatus.OK),updateService.updateStoreDetails("REQ-76bc9").getStatusCode());
	 //  assertEquals(new ResponseEntity<>(HttpStatus.OK),returnEmployeeRequest.getStatusCode());
	}
	
	
	
	
	@Test
	void testforSaveRequest() {
		StoreRequestRepo storeRequestRepomock=mock(StoreRequestRepo.class);
		StoreRequest storeRequest=new StoreRequest();
		storeRequest.setRequest_Id("REQ21162");
		storeRequest.setAddress("california");
		storeRequest.setPhone_Number("9524278");
		storeRequest.setArea_Region_Code("california 345");
		
		storeRequest.setStore_Id("2abfv");
		
		UpdateService updateService=new UpdateService(storeRequestRepomock);
		String tokenString="AAA";
		ResponseEntity<StoreRequest> returnEmployeeRequest=updateService.saveStore(storeRequest, tokenString);	
		assertEquals(storeRequest.getStore_Id(),returnEmployeeRequest.getBody().getStore_Id());
	}
	
	@Test
	void retrieveMatching() {
		assertEquals(new ResponseEntity<>(storeRepo.findByrequest_Id(),HttpStatus.OK),updateService.retrieveMatching());
	}
	
}
