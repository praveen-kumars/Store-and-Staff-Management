package com.managestoreservice.storedata.controller;

import java.util.List;

import org.springframework.beans.factory.ListableBeanFactoryExtensionsKt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.managestoreservice.storedata.DTO.StoreDTO;
import com.managestoreservice.storedata.authclient.AuthClient;
import com.managestoreservice.storedata.exception.InvalidTokenException;
import com.managestoreservice.storedata.model.Store;
import com.managestoreservice.storedata.model.StoreRequest;
import com.managestoreservice.storedata.repository.StoreRequestRepo;
import com.managestoreservice.storedata.service.UpdateService;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin(origins = {"*"})
@Slf4j
public class UpdateStoreController {
	
	
	@Autowired
	private AuthClient authClient;
	
	@Autowired 
	private UpdateService updateService;
	
	@Autowired
	private StoreRequestRepo storeRequestRepo;
	
	
	
	

	@GetMapping(path = "/retrieve/{storeName}")
	public ResponseEntity<Store> retrievestore(@PathVariable("storeName") String storeName,@RequestHeader(name="Authorization",required = true) String token)
	throws InvalidTokenException{
		log.info("BEGIN - [Retrieve Store Details]");
		log.info("BEGIN -[Token validity Check]");
		
	if(!authClient.getValidity(token).isValidStatus()) {
			throw new InvalidTokenException("Token is either expired or invalid");
	}
	log.info("END - [Data Retrieved]");
	return updateService.getStoreDetails(storeName);
	}
	
	@PostMapping(path = "/add-store")
	public ResponseEntity<StoreRequest> addStore(@RequestBody StoreRequest storeRequest,@RequestHeader(name = "Authorization",required=true) String token)
	throws InvalidTokenException,NullPointerException{
		log.info("BEGIN -[Adding store request]");
		if(!authClient.getValidity(token).isValidStatus()) {
			throw new InvalidTokenException("Token is either invalid");
		}
		System.out.println(storeRequest);
		log.info("END -[Store request added]");
		return updateService.saveStore(storeRequest, token);
	}
	
	@PutMapping(path = "/updatestore/{requestId}")
	public ResponseEntity updateStore(@PathVariable String requestId){
		
		log.info("BEGIN - [Updates Data]");
		log.info("END -[Data Updated]");
		return updateService.updateStoreDetails(requestId);
		
	}
	
	@GetMapping(path = "/retrieveall")
	public ResponseEntity<List<StoreRequest>> retrieverequestData(@RequestHeader(name="Authorization",required = true) String token)
	throws InvalidTokenException{
		log.info("BEGIN - [Retrieve all request data]");
	
	if(!authClient.getValidity(token).isValidStatus()) {
		throw new InvalidTokenException("Token is either expired or invalid");
		//return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	log.info("Request table retrieved]");
	return ResponseEntity.ok(storeRequestRepo.findAll());
	
	}
	
	@GetMapping(path = "/retrievematch")
	public ResponseEntity<List<Store>> retrievematchingData(@RequestHeader(name="Authorization",required = true) String token)
	throws InvalidTokenException{
		if(!authClient.getValidity(token).isValidStatus()) {
			throw new InvalidTokenException("Token is either expired or invalid");
		}
			log.info("BEGIN - Matching data in store table]");
			log.info("END -[Matching data retrieved]");
			System.out.println(storeRequestRepo.findAll());
         return updateService.retrieveMatching();
	
	}
	@DeleteMapping(path = "/deleterequest/{store_Id}")
	public ResponseEntity deleteRequest(@PathVariable String store_Id){
		log.info("BEGIN -[Delete Store request]");
		log.info("END- [Store request deleted]");	
		return updateService.deleteRequest(store_Id);
	}

}
