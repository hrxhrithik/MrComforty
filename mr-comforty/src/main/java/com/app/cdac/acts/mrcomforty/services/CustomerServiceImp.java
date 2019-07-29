package com.app.cdac.acts.mrcomforty.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.cdac.acts.mrcomforty.dao.AddressDao;
import com.app.cdac.acts.mrcomforty.dao.BookingDao;
import com.app.cdac.acts.mrcomforty.dao.CustomerDao;
import com.app.cdac.acts.mrcomforty.pojos.Address;
import com.app.cdac.acts.mrcomforty.pojos.Booking;
import com.app.cdac.acts.mrcomforty.pojos.Customer;

@Service
public class CustomerServiceImp implements CustomerService {
	
	@Autowired
	private CustomerDao dao;
	
	@Autowired
	private BookingDao daoBook;
	
	@Autowired
	private AddressDao addDao;
	
	
	public CustomerServiceImp() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public long registerUser(Customer user, Address address) {
		System.out.println("MrComfortyImp:in registerUser()");
		user.addAddress(address);
		return dao.save(user).getUserId();
	}

	@Override
	public Customer loginCustomer(String email, String password) {
		System.out.println("MrComfortyImp:in loginCustomer()");
		return dao.login(email, password);
	}

	@Override
	public Customer getCustomerById(int customerId) {
		System.out.println("MrComfortyImp:in getCustomerById()");
		return dao.getCustomerDetailsById(customerId);
	}

	@Override
	public List<Booking> getCurrentBooking(int customerId) {
		System.out.println("MrComfortyImp:in getCurrentBooking()");
		List<Booking> b=daoBook.getCurrentBookingDetailsByCustomerId(customerId);
		//System.out.println("getCurrentBooking  "+b);
		return b;
	}

	@Override
	public List<Booking> getHistoryOfBooking(int customerId) {
		System.out.println("MrComfortyImp:in getHistoryOfBooking()");
		return daoBook.getHistoryOfBookingDetailsByCustomerId(customerId);
	}


	@Override
	public Customer getAddressListByCustomerId(int customerId) {
		return dao.getAddressListByCustomerId(customerId);
	}

	@Override
	public Integer addAddress(Address address, int customerId) {
		Customer customer=dao.getCustomerDetailsById(customerId);
		customer.addAddress(address);
		dao.save(customer);
		return customerId;
	}

	@Override
	public Integer removeAddress(int addressId, int customerId) {
		Customer customer=dao.getCustomerDetailsById(customerId);
		 Address address=addDao.findById(addressId).get();
		customer.removeAddress(address);
		dao.save(customer);
		return customerId;
	}

}
