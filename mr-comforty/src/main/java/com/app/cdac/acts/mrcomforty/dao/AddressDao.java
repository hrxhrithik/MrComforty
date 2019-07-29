package com.app.cdac.acts.mrcomforty.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.cdac.acts.mrcomforty.pojos.Address;

@Repository
public interface AddressDao extends JpaRepository<Address, Integer>{

}
