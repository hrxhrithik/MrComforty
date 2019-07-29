package com.app.cdac.acts.mrcomforty.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.cdac.acts.mrcomforty.dao.AddressDao;
import com.app.cdac.acts.mrcomforty.pojos.Address;


@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressDao dao;
	
	@Override
	public Address getAddressByAddressId(int addressId) {
		return dao.findById(addressId).get();
	}
}
