package com.managestoreservice.storedata.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.managestoreservice.storedata.model.Store;
import com.managestoreservice.storedata.model.StoreRequest;


@Repository
public interface StoreRequestRepo extends JpaRepository<StoreRequest,String> {

	@Query(value = "select * from store_request s where s.request_id=:n",nativeQuery=true)
	public StoreRequest findByrequest_Id(@Param("n") String requestId);



	



   
 

}
