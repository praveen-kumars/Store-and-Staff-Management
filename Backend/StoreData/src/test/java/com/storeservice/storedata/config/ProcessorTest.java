package com.storeservice.storedata.config;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.storeservice.storedata.model.Store;

@SpringBootTest
public class ProcessorTest {
	
	String store_Id="76bc5";
	String store_Id_FailString="453424";
	Store store=new Store("76bc5","","","","","","","","","",null);
	
	@Autowired
	StoreProcessor storeProcessor;
	
	@Test
	void validationForStoreIdTest() throws Exception {
		assertEquals(store,storeProcessor.process(store));
	}

	
}
