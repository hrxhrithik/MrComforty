 package com.app.cdac.acts.mrcomforty.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.cdac.acts.mrcomforty.pojos.Address;
import com.app.cdac.acts.mrcomforty.pojos.Booking;
import com.app.cdac.acts.mrcomforty.services.AddressService;
import com.app.cdac.acts.mrcomforty.services.BookingService;
import com.app.cdac.acts.mrcomforty.services.ServiceProviderService;
import com.app.cdac.acts.mrcomforty.controller.BookingController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/mrcomforty")
public class BookingController {

	@Autowired
	private BookingService service;
	
	@Autowired
	private ServiceProviderService ser_pro_service;
	
	@Autowired
	private AddressService addressService;
	
	@PostMapping("/booking/{addressId}")
	public  ResponseEntity<?> saveBooking(@RequestBody Booking booking,@PathVariable int addressId)
	{
		
			System.out.println("json data is="+ booking);
			int workingStatus=1;
			Address address=addressService.getAddressByAddressId(addressId);
			int bookingId=service.saveBooking(booking);
			Booking book=service.getBookingById(bookingId);
			book.setRelatedAddresse(address);
			int ser_pro_id=service.getServiceProviderIdByBookingId(bookingId);
			service.UpdateBookingStatus(workingStatus, bookingId);
			System.out.println("working status"+ workingStatus);
			System.out.println("pro_id"+ser_pro_id);
			ser_pro_service.updateServiceProviderWorkingStatus( workingStatus, ser_pro_id);
			service.sendcust(bookingId);
			service.sendProv(bookingId);
			return ResponseEntity.ok().body(bookingId);
			
		
	}
	
	@GetMapping("/bookingid/{booking_id}")
	public ResponseEntity<?> getBookingById(@PathVariable int booking_id)
	{
		try{
			
			Booking details=service.getBookingById(booking_id);
			System.out.println("deatils "+details);
			return ResponseEntity.ok().body(details);
		}catch(Exception e)
		{
			return ResponseEntity.ok().body(null);
		}
	}
	

	
	@GetMapping("/customerbook/{customer_id}")
	public ResponseEntity<?> getBookingByCustomerId(@PathVariable int customer_id)
	{
		try{
			
			Booking details=service.getBookingByCustomerId(customer_id);
			System.out.println("deatils "+details);
			return ResponseEntity.ok().body(details);
		}catch(Exception e)
		{
			return ResponseEntity.ok().body(null);
		}
	}
	
	@GetMapping("/currentcustomerbook/{customer_id}")
	public ResponseEntity<?> getCurrentBookingDetailsByCustomerId(@PathVariable int customer_id)
	{
		try{
			System.out.println("in booking current customer");
			List<Booking> details=service.getCurrentBookingDetailsByCustomerId(customer_id);
			System.out.println("deatils "+details);
			return ResponseEntity.ok().body(details);
		}catch(Exception e)
		{
			return ResponseEntity.ok().body(null);
		}
	}
	
	@GetMapping("/historycustomerbook/{customer_id}")
	public ResponseEntity<?> getHistoryBookingDetailsByCustomerId(@PathVariable int customer_id)
	{
		try{
			
			List<Booking> details=service.getHistoryOfBookingDetailsByCustomerId(customer_id);
			System.out.println("deatils "+details);
			return ResponseEntity.ok().body(details);
		}catch(Exception e)
		{
			return ResponseEntity.ok().body(null);
		}
	}
	
	
	
	

}