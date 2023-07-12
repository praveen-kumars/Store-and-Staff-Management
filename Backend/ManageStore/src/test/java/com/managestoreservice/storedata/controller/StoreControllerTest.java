package com.managestoreservice.storedata.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

import com.managestoreservice.storedata.ManageStoreApplication;
import com.managestoreservice.storedata.DTO.ValidatingDTO;
import com.managestoreservice.storedata.authclient.AuthClient;
import com.managestoreservice.storedata.exception.InvalidTokenException;
import com.managestoreservice.storedata.model.Store;
import com.managestoreservice.storedata.model.StoreRequest;
import com.managestoreservice.storedata.repository.StoreRequestRepo;
import com.managestoreservice.storedata.service.UpdateService;

@SpringBootTest
public class StoreControllerTest {
	
	@InjectMocks
	UpdateStoreController updateStoreController;
	
	@Mock
	AuthClient authClient;
	
	@Mock
	UpdateService updateService;
	
	String tokenString="AAA";
	String storeName="76bc9";
	StoreRequest storeRequest=new StoreRequest("76bc9","REQ-76bc9","G14-11 th street","Los Angles, California, 12343","8220551256","9am-7pm","9am-7pm","9am-7pm","9am-7pm","9am-7pm","","", null);
	Store store=new Store("76bc9","G14-11 th street","Los Angles, California, 12343","8220551256","9am-7pm","9am-7pm","9am-7pm","9am-7pm","9am-7pm","","",null);
	
	
	@Test
	void StoreTestControllerNotNullTest() {
		UpdateStoreController updateStoreController=new UpdateStoreController();
		assertThat(updateStoreController).isNotNull();
	}
	
	@Test
	void retrieveStoreTest() {
		ValidatingDTO validatingDTO=new ValidatingDTO(true);
		Store store=new Store("76bc9","G14-11 th street","Los Angles, California, 12343","8220551256","9am-7pm","9am-7pm","9am-7pm","9am-7pm","9am-7pm","","",null);
		
		when(authClient.getValidity(tokenString)).thenReturn(validatingDTO);
		when(updateService.getStoreDetails(store.getStore_Id())).thenReturn(new ResponseEntity<Store>(store,HttpStatus.OK));
		assertEquals(store.getStore_Id(),updateStoreController.retrievestore(store.getStore_Id(),tokenString).getBody().getStore_Id());
	}
	
	@Test
	void invalidtokenretrieveStore() {
		ValidatingDTO validatingDTO=new ValidatingDTO(false);
		when(authClient.getValidity(tokenString)).thenReturn(validatingDTO);
		assertThrows(InvalidTokenException.class, ()->
		updateStoreController.retrievestore(storeName, tokenString));
	}
	
	@Test
	void addStoreTest() {
		StoreRequest storeRequest=new StoreRequest("76bc9","G14-11 th street","Los Angles, California, 12343","8220551256","9am-7pm","9am-7pm","9am-7pm","9am-7pm","9am-7pm","","",null, null);
		ValidatingDTO validatingDTO=new ValidatingDTO(true);
		when(authClient.getValidity(tokenString)).thenReturn(validatingDTO);
		when(updateService.saveStore(storeRequest, tokenString)).thenReturn(new ResponseEntity<>(storeRequest,HttpStatus.OK));
		assertEquals(storeRequest.getStore_Id(),updateStoreController.addStore(storeRequest,tokenString).getBody().getStore_Id());	
	}
	
	@Test
	void invalidtokenaddStore() {		
		ValidatingDTO validatingDTO=new ValidatingDTO(false);
		when(authClient.getValidity(tokenString)).thenReturn(validatingDTO);
		assertThrows(InvalidTokenException.class, ()->
		updateStoreController.addStore(storeRequest, tokenString));
	}
	
	@Test
	void updateStoreTest() {
		when(updateService.updateStoreDetails(storeRequest.getRequest_Id())).thenReturn(new ResponseEntity<>(storeRequest,HttpStatus.OK));
		assertEquals(HttpStatus.OK,updateStoreController.updateStore(storeRequest.getRequest_Id()).getStatusCode());
	}
	
	@Test
	void retrieverequestDataTest() {
		ValidatingDTO validatingDTO=new ValidatingDTO(true);
		when(authClient.getValidity(tokenString)).thenReturn(validatingDTO);
		//when(updateService.retrieveMatching()).thenReturn(new ResponseEntity<List<Store>>(storeRequest,HttpStatus.OK));
		//assertEquals(HttpStatus.OK,updateStoreController.retrieverequestData(tokenString).getStatusCode());	
		updateStoreController.retrieverequestData(tokenString);
	}
	@Test
	void invalidtokenretrieveData() {		
		ValidatingDTO validatingDTO=new ValidatingDTO(false);
		when(authClient.getValidity(tokenString)).thenReturn(validatingDTO);
		
		
		assertThrows(InvalidTokenException.class, ()->
		updateStoreController.retrieverequestData(tokenString));
	}
	
	@Test
	void retrievematchingDataTest1() {
		UpdateService updateService=mock(UpdateService.class);
		StoreRequestRepo storeRequestRepo=mock(StoreRequestRepo.class);
		ValidatingDTO validatingDTO=new ValidatingDTO(true);
		when(authClient.getValidity(tokenString)).thenReturn(validatingDTO);
		List<Store> stores=Arrays.asList(store);
		when(updateService.retrieveMatching()).thenReturn(new ResponseEntity<>(stores,HttpStatus.OK));
		when(updateService.retrieveMatching()).thenReturn(new ResponseEntity<List<Store>>(HttpStatus.OK));
		//ResponseEntity<List<Store>> storeliStores=updateStoreController.retrievematchingData(tokenString);
		//assertEquals(HttpStatus.OK, storeliStores.getStatusCode());
	}
	
	@Test
	void retrievematchingDataTest() {
		ValidatingDTO validatingDTO=new ValidatingDTO(true);
		when(authClient.getValidity(tokenString)).thenReturn(validatingDTO);
	    when(updateService.retrieveMatching()).thenReturn(new ResponseEntity<List<Store>>(HttpStatus.OK));
		assertEquals(HttpStatus.OK, updateStoreController.retrievematchingData(tokenString).getStatusCode());
	}
	
	@Test
	void invalidtokenretrieveMatchingData() {		
		ValidatingDTO validatingDTO=new ValidatingDTO(false);
		when(authClient.getValidity(tokenString)).thenReturn(validatingDTO);
		assertThrows(InvalidTokenException.class, ()->
		updateStoreController.retrievematchingData(tokenString));
	}
	
	@Test
	void deleteRequestTest() {
		when(updateService.deleteRequest(store.getStore_Id())).thenReturn(new ResponseEntity<>(HttpStatus.OK));
		assertEquals(new ResponseEntity<>(HttpStatus.OK),updateStoreController.deleteRequest(store.getStore_Id()));		
	}
	
	
	
	

	
	

}
