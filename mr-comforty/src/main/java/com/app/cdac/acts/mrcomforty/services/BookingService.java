package com.app.cdac.acts.mrcomforty.services;

import java.util.Date;
import java.util.List;

import com.app.cdac.acts.mrcomforty.pojos.Booking;

public interface BookingService {
	
	public int saveBooking(Booking book);	
	public Booking getBookingById(int booking_id);
	public void UpdateBookingHours(int hours,int booking_id);
	public void UpdateBookingStatus(int status,int booking_id);
	public Booking getBookingByCustomerId(int customer_id);
	public Booking getBookingByServiceProId(int serviceprovider_id);
	public int getServiceProviderIdByBookingId(int bookingId);
	public Integer getAllServiceProvidersIdNotBookedAtDate(int serviceProviderId,Date date);
	public List<Booking> getCurrentBookingDetailsByCustomerId(int id);
	public List<Booking> getHistoryOfBookingDetailsByCustomerId(int id);
	public String getProviderName(int id);
	public String getProviderNumber(int id);
	public int getCustomerId(int id);
	public int getProviderId(int id);
	public String getCustomerName(int id);
	public String getCustomerNumber(int id);
	public String sendSms(String mess,String number);
	public void sendcust(int bookingId);
	public void sendProv(int bookingId);
	

}
