package com.app.cdac.acts.mrcomforty.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.cdac.acts.mrcomforty.dao.ServiceDao;
import com.app.cdac.acts.mrcomforty.pojos.Services;

@Service
public class ServiceServiceImp implements ServiceService {

	@Autowired
	private ServiceDao serviceDao;
	
	public ServiceServiceImp() {
		System.out.println("ServiceServiceImp:In Constructor");
	}

	@Override
	public Services getServiceById(int id) {
		return serviceDao.findById(id).get();
	}
	
	@Override
	public List<Services> getAllServices(){
		return serviceDao.findAll();
	}

}
