package com.app.cdac.acts.mrcomforty.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.cdac.acts.mrcomforty.pojos.Location;

public interface LocationDao extends JpaRepository<Location, Integer> {

	@Query("select l from Location l where l.location=?1")
	Location  findByLocationName(String location);
}
