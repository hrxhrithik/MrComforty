package com.app.cdac.acts.mrcomforty.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.cdac.acts.mrcomforty.pojos.Customer;

@Repository
public interface CustomerDao extends JpaRepository<Customer, Long>{
	
	
	@Query(value="select * from customer where email=?1 and password=?2",nativeQuery=true)
	public Customer login(String email,String password);
	
	@Query(value="select * from customer   where customer_id=?1",nativeQuery=true)
	Customer getCustomerDetailsById(int id);
	
	@Query(value="select * from customer where customer_id=?1",nativeQuery=true)
	Customer getAddressListByCustomerId(int customerId);
	
	
}
