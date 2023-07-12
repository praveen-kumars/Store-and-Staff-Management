package com.managestoreservice.storedata.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.managestoreservice.storedata.model.Store;

public interface StoreRepo extends JpaRepository<Store,String> {
	@Query(value = "select * from master_store_data inner join store_request on master_store_data.shop_id=store_request.shop_id",nativeQuery=true)
	List<Store> findByrequest_Id();

	


	

}
