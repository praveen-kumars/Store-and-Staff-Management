package com.storeservice.storedata.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.storeservice.storedata.model.Store;

public interface StoreRepo extends JpaRepository<Store, String>{
	

}
