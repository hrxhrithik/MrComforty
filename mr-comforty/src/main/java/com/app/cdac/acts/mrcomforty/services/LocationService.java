package com.app.cdac.acts.mrcomforty.services;

import java.util.List;

import com.app.cdac.acts.mrcomforty.pojos.Location;

public interface LocationService {
	List<Location> getAllLocations();
	Location getLocationById(int id);
}
