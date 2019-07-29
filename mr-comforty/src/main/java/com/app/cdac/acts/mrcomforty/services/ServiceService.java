package com.app.cdac.acts.mrcomforty.services;

import java.util.List;

import com.app.cdac.acts.mrcomforty.pojos.Services;

public interface ServiceService {
	Services getServiceById(int id);
	List<Services> getAllServices();
}
