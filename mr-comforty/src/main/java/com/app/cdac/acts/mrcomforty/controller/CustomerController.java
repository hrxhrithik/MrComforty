package com.app.cdac.acts.mrcomforty.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.cdac.acts.mrcomforty.pojos.Address;
import com.app.cdac.acts.mrcomforty.pojos.Booking;
import com.app.cdac.acts.mrcomforty.pojos.Customer;
//import com.app.pojos.User;
import com.app.cdac.acts.mrcomforty.pojos.CustomerAddress;
import com.app.cdac.acts.mrcomforty.services.CustomerService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/mrcomforty")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	public CustomerController() {
		System.out.println("mrcomfortyController: In constructor");
	}

	@PostMapping("/user")
	public ResponseEntity<?> registerCustomer(@RequestBody CustomerAddress userAddres) {
		System.out.println("Controller" + userAddres);
		try {
			long id = customerService.registerUser(userAddres.getCustomer(), userAddres.getAddress());
			System.out.println(id);
			return ResponseEntity.ok().body(id);
		} catch (Exception e) {
			
			return ResponseEntity.ok().body(-1);
		}
	}

	@PostMapping("/userlogin")
	public ResponseEntity<?> loginCustomer(@RequestParam String email,@RequestParam String password) {
		System.out.println("--------------------------here---------------------------");
		try {
			return ResponseEntity.ok().body(customerService.loginCustomer(email, password));
			
		} catch (Exception e) {
			return ResponseEntity.ok().body(null);
		}
	}
	
	@GetMapping("/customerDetails/{customerId}")
	public ResponseEntity<?> getCustomerById(@PathVariable int customerId)
	{
		try {
			System.out.println(customerId);
			Customer cust=customerService.getCustomerById(customerId);
			System.out.println(cust);
			return ResponseEntity.ok().body(cust);
		} catch (Exception e) {
			return ResponseEntity.ok().body(-1);
		}
		
	}
	
	@GetMapping("/customerCurrentBooking/{customerId}")
	public ResponseEntity<?> getCurrentBookingById(@PathVariable int customerId)
	{
		try {
			System.out.println("customerCurrentBooking controller");
			List<Booking> b=customerService.getCurrentBooking(customerId);
			System.out.println("current Booking   "+b);
			return ResponseEntity.ok().body(b);
		} catch (Exception e) {
			return ResponseEntity.ok().body(null);
		}
		
	}
	
	@GetMapping("/customerHistoryBooking/{customerId}")
	public ResponseEntity<?> getHistoryBookingById(@PathVariable int customerId)
	{
		try {
			return ResponseEntity.ok().body(customerService.getHistoryOfBooking(customerId));
		} catch (Exception e) {
			return ResponseEntity.ok().body(null);
		}
		
	}
	
	@GetMapping("/customeraddresslist/{customerId}")
	public ResponseEntity<?>getAddressListByCustomerId(@PathVariable int customerId)
	{
		try {
			return ResponseEntity.ok().body(customerService.getAddressListByCustomerId(customerId).getAddresses());
		} catch (Exception e) {
			return ResponseEntity.ok().body(null);
		}
		
	}
	
	@PostMapping("/addAddress/{customerId}")
	public ResponseEntity<?>addAddress(@RequestBody Address address,@PathVariable int customerId)
	{
		System.out.println(address);
		try {
			return ResponseEntity.ok().body(customerService.addAddress(address, customerId));
		} catch (Exception e) {
			return ResponseEntity.ok().body(-1);
		}
		
	}
	
	@DeleteMapping("/removeCustomerAddress/{customerId}/{addressId}")
	public ResponseEntity<?>removeAddress(@PathVariable int customerId,@PathVariable int addressId)
	{
;
		try {
			return ResponseEntity.ok().body(customerService.removeAddress(addressId, customerId));
		} catch (Exception e) {
			return ResponseEntity.ok().body(-1);
		}
		
	}
	
	
}
