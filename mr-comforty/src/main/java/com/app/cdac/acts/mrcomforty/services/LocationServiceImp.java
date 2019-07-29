package com.app.cdac.acts.mrcomforty.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.cdac.acts.mrcomforty.dao.LocationDao;
import com.app.cdac.acts.mrcomforty.pojos.Location;

@Service
public class LocationServiceImp implements LocationService {

	@Autowired
	private LocationDao locationDao;
	
	public LocationServiceImp() {
	}
	
	@Override
	public List<Location> getAllLocations() {
		return locationDao.findAll();
	}

	@Override
	public Location getLocationById(int id) {
		return locationDao.findById(id).get();
	}

}
