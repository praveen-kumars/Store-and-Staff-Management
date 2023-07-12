package com.managestoreservice.storedata.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.managestoreservice.storedata.DTO.StoreDTO;
import com.managestoreservice.storedata.model.Store;
import com.managestoreservice.storedata.model.StoreRequest;
import com.managestoreservice.storedata.repository.StoreRepo;
import com.managestoreservice.storedata.repository.StoreRequestRepo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UpdateService {
	
	@Autowired
	StoreRepo storeRepo;
	
	@Autowired
	StoreRequestRepo storeRequestRepo;

	public UpdateService(StoreRequestRepo storeRequestrepomock) {
		// TODO Auto-generated constructor stub
		this.storeRequestRepo=storeRequestrepomock;
	}

	public ResponseEntity<Store> getStoreDetails(String storeName) {
		log.info("BEGIN -[Get store details]");
		Store storedetail=storeRepo.findById(storeName).get();
		if(storeRequestRepo.existsById(storeName)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		else {
			log.info("END-[Store details found]");
			return new ResponseEntity<>(storedetail,HttpStatus.OK);	
		}
	}

	public ResponseEntity<StoreRequest> saveStore(StoreRequest request_Id, String token) {
		log.info("BEGIN - [Save request store data]");
		if(storeRequestRepo.existsById(request_Id.getStore_Id())) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		else {
			request_Id.setRequest_Id("REQ-"+request_Id.getStore_Id());
			storeRequestRepo.save(request_Id);
			log.info("END-[Request data saved]");
			return new ResponseEntity<>(request_Id,HttpStatus.OK);
			
		}	
	}

	public ResponseEntity updateStoreDetails(String requestId) {
		log.info("BEGIN - [Update store details]");
		StoreRequest storedetailRequest=storeRequestRepo.findByrequest_Id(requestId);
		Store store=storeRepo.findById(storedetailRequest.getStore_Id()).get();
		store.setAddress(storedetailRequest.getAddress());
		store.setPhone_Number(storedetailRequest.getPhone_Number());
		store.setArea_Region_Code(storedetailRequest.getArea_Region_Code());
		store.setMon_Hours(storedetailRequest.getMon_Hours());
		store.setTue_Hours(storedetailRequest.getTue_Hours());
		store.setWed_Hours(storedetailRequest.getWed_Hours());
		store.setThru_Hours(storedetailRequest.getThru_Hours());
		store.setFri_Hours(storedetailRequest.getFri_Hours());
		store.setSat_Hours(storedetailRequest.getSat_Hours());
		store.setSun_Hours(storedetailRequest.getSun_Hours());
		storeRepo.save(store);
		storeRequestRepo.deleteById(storedetailRequest.getStore_Id());
		log.info("END - [Details succesfully updated]");
		return new ResponseEntity<>(HttpStatus.OK);
	}

	public ResponseEntity<List<Store>> retrieveMatching() {
		return new ResponseEntity<>(storeRepo.findByrequest_Id(),HttpStatus.OK);
	}

	public ResponseEntity deleteRequest(String store_Id) {
		log.info("BEGIN -[Delete request details by id]");
		storeRequestRepo.deleteById(store_Id);	
		log.info("END - [Store request deleted]");
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
