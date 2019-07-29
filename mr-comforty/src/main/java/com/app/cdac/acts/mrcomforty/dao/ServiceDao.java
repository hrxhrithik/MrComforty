package com.app.cdac.acts.mrcomforty.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.cdac.acts.mrcomforty.pojos.Services;

public interface ServiceDao extends JpaRepository<Services, Integer> {

	@Query("select s from Services s where s.serviceName=?1")
	Services  findByServiceName(String name);
}
